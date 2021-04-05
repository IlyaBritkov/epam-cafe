package com.epam.db;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Singleton
 **/
public class ConnectionPoolImpl implements ConnectionPool {
    private final static Logger logger = LoggerFactory.getLogger(ConnectionPoolImpl.class);

    private final static DBConfiguration dbConfiguration = DBConfiguration.getInstance();

    @Getter
    private final String URL = dbConfiguration.getURL();
    @Getter
    private final String USER = dbConfiguration.getUSER();
    @Getter
    private final String PASSWORD = dbConfiguration.getPASSWORD();

    private final static int INITIAL_POOL_SIZE = dbConfiguration.getINITIAL_POOL_SIZE();
    private final int MAX_POOL_SIZE = dbConfiguration.getMAX_POOL_SIZE();
    private final int MAX_TIMEOUT_VALIDATE_CONNECTION = dbConfiguration.getMAX_TIMEOUT_VALIDATE_CONNECTION();

    private final Queue<Connection> availableConnections = new LinkedBlockingQueue<>();
    private final Queue<Connection> usedConnections = new LinkedBlockingQueue<>();

    private static ConnectionPoolImpl INSTANCE = getInstance();

    private ConnectionPoolImpl() {
    }

    public static ConnectionPoolImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPoolImpl();
            init();
            logger.info("ConnectionPool was initialized");
        }
        return INSTANCE;
    }

    private static void init() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            INSTANCE.availableConnections.add(createConnection(INSTANCE.getURL(), INSTANCE.getUSER(), INSTANCE.getPASSWORD()));
        }
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Exception occurred while trying get connection: {}", throwables.toString());
            throw new RuntimeException("Creation of connection is failed: " + throwables);
        }
    }

    @Override
    public Connection getConnection() {
        if (availableConnections.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                availableConnections.add(createConnection(URL, USER, PASSWORD));
                logger.debug("Added one more connection to the connection pool: {}", availableConnections.peek());
            } else {
                RuntimeException exception = new RuntimeException("Maximum pool size reached, no available connections!");
                logger.error("RuntimeException occurred: {}", exception.toString());
                throw exception;
            }
        }

        Connection connection = availableConnections.remove();

        try {
            if (!connection.isValid(MAX_TIMEOUT_VALIDATE_CONNECTION)) {
                connection = createConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Exception occurred while connection validation: {}", throwables.toString());
        }

        usedConnections.add(connection);
        logger.debug("Connection {} was taken", connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        availableConnections.add(connection);
        logger.debug("Connection {} was returned to the connection pool", connection);
        return usedConnections.remove(connection);
    }

    @Override
    public int getSize() {
        return availableConnections.size() + usedConnections.size();
    }

    public void shutdown() {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : availableConnections) {
            try {
                c.close();
            } catch (SQLException throwables) {
                logger.error("Exception occurred: {}", throwables.toString());
                throwables.printStackTrace();
            }
        }
        availableConnections.clear();
        logger.debug("Connection pool was shutdown");
    }
}
