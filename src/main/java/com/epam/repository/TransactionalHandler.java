package com.epam.repository;

import com.epam.db.ConnectionPool;
import com.epam.db.ConnectionPoolImpl;
import com.epam.db.TransactionIsolationType;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionalHandler<T> {

    /**
     * Executes {@param transaction} with isolation level {@code TRANSACTION_REPEATABLE_READ}
     * **/
    public T transactional(Transaction<T> transaction) throws SQLException {
        return transactional(TransactionIsolationType.TRANSACTION_REPEATABLE_READ, transaction);
    }

    /**
     * Executes transaction with specified isolation level
     * @param isolationType - one of 5 possible isolation level from {@link TransactionIsolationType}
     * @param transaction - transaction for execution
     * **/
    public T transactional(TransactionIsolationType isolationType, Transaction<T> transaction) throws SQLException {
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        Connection connection = connectionPool.getConnection();
        connection.setAutoCommit(false);
        setIsolationType(connection, isolationType);

        T transactionResult = transaction.execute(connection);

        connection.commit();
        connectionPool.releaseConnection(connection);
        return transactionResult;
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
