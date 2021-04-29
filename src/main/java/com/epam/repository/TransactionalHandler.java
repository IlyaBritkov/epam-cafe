package com.epam.repository;

import com.epam.db.ConnectionPool;
import com.epam.db.ConnectionPoolImpl;
import com.epam.db.TransactionIsolationType;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class TransactionalHandler<T> {

    /**
     * Executes {@param transaction} with isolation level {@code TRANSACTION_REPEATABLE_READ}
     */
    public Optional<T> transactional(Transaction<T> transaction) throws SQLException {
        return transactional(TransactionIsolationType.TRANSACTION_REPEATABLE_READ, transaction);
    }

    /**
     * Executes transaction with specified isolation level
     *
     * @param isolationType - one of 5 possible isolation level from {@link TransactionIsolationType}
     * @param transaction   - transaction for execution
     **/
    public Optional<T> transactional(TransactionIsolationType isolationType, Transaction<T> transaction) {
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        Connection connection = connectionPool.getConnection();
        T transactionResult = null;
        try {
            connection.setAutoCommit(false);
            setIsolationType(connection, isolationType);

            transactionResult = transaction.execute(connection);

            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
                log.error("Something's gone wrong during transaction execution and rollback was performed: {}",
                        throwables.toString());
            } catch (SQLException e) {
                log.error("Something's gone wrong during attempt to rollback transaction: {}", e.toString());
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        connectionPool.releaseConnection(connection);
        return Optional.ofNullable(transactionResult);
    }

    private void setIsolationType(Connection connection, TransactionIsolationType isolationType) throws SQLException {
        switch (isolationType) {
            case TRANSACTION_NONE:
                connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
                break;
            case TRANSACTION_READ_UNCOMMITTED:
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                break;
            case TRANSACTION_READ_COMMITTED:
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                break;
            case TRANSACTION_REPEATABLE_READ:
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                break;
            case TRANSACTION_SERIALIZABLE:
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                break;
        }
    }
}
