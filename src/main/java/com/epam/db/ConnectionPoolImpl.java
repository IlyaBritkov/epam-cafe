package com.epam.db;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton
 **/

// todo: add thread safety
public class ConnectionPoolImpl implements ConnectionPool {
    private final static Logger logger = LoggerFactory.getLogger(ConnectionPoolImpl.class);

    private static volatile ConnectionPoolImpl INSTANCE;
    @Getter
    private String url; // todo
    @Getter
    private String user; // todo
    @Getter
    private String password; // todo

    private static int INITIAL_POOL_SIZE; // todo
    private static int MAX_POOL_SIZE; // todo

    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();

    public static ConnectionPoolImpl getInstance() throws SQLException {
        ConnectionPoolImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (ConnectionPoolImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new ConnectionPoolImpl();
                }
            }
        }
        return localInstance;
    }

    private ConnectionPoolImpl() throws SQLException {
        init();
        logger.info("Connection pool was initialized");
    }

    private void init() throws SQLException {
        this.connectionPool = new ArrayList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            INSTANCE.connectionPool.add(createConnection(this.getUrl(), this.getUser(), this.getPassword()));
        }
    }

    private Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        logger.trace("Connection {} was taken", connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        logger.trace("Connection {} was returned to connection pool", connection);
        return usedConnections.remove(connection);
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
