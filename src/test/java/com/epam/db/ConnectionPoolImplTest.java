package com.epam.db;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConnectionPoolImplTest {
    private final static ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();

    @Test
    void getInstanceShouldReturnSingleton() {
        assertSame(ConnectionPoolImpl.getInstance(), ConnectionPoolImpl.getInstance());
    }

    @Test
    void getConnectionShouldReturnDifferentConnections() {
        assertNotSame(connectionPool.getConnection(), connectionPool.getConnection());
    }

    @Test
    @SuppressWarnings("unchecked")
    void releaseConnectionShouldReturnUsedConnectionToAvailableConnections() throws NoSuchFieldException, IllegalAccessException {
        Connection connection = connectionPool.getConnection();

        Field field = connectionPool.getClass().getDeclaredField("availableConnections");
        field.setAccessible(true);

        int expectedSize = ((Queue<Connection>) field.get(connectionPool)).size() + 1;
        connectionPool.releaseConnection(connection);
        int actualSize = ((Queue<Connection>) field.get(connectionPool)).size();

        field.setAccessible(false);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(10)
    @SuppressWarnings("unchecked")
    void getSizeAtTheBeginningOfProgramShouldReturnINITIAL_POOL_SIZE() throws NoSuchFieldException, IllegalAccessException {
        int expectedSizeOfAvailableConnections = DBConfiguration.getInstance().getINITIAL_POOL_SIZE();

        Field availableConnectionsField = ConnectionPoolImpl.class.getDeclaredField("availableConnections");
        availableConnectionsField.setAccessible(true);

        int actualSizeOfAvailableConnections = ((Queue<Connection>) availableConnectionsField.get(connectionPool)).size();

        availableConnectionsField.setAccessible(false);
        assertEquals(expectedSizeOfAvailableConnections, actualSizeOfAvailableConnections);


        int expectedSizeOfUsedConnections = 0;

        Field usedConnectionsField = ConnectionPoolImpl.class.getDeclaredField("usedConnections");
        usedConnectionsField.setAccessible(true);

        int actualSizeOfUsedConnections = ((Queue<Connection>) usedConnectionsField.get(connectionPool)).size();

        usedConnectionsField.setAccessible(false);
        assertEquals(expectedSizeOfUsedConnections, actualSizeOfUsedConnections);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getSizeShouldReturnSumOfAvailableConnectionsAndUsedConnections() throws NoSuchFieldException, IllegalAccessException {
        int expectedSize = connectionPool.getSize();

        Field availableConnectionsField = ConnectionPoolImpl.class.getDeclaredField("availableConnections");
        availableConnectionsField.setAccessible(true);

        Field usedConnectionsField = ConnectionPoolImpl.class.getDeclaredField("usedConnections");
        usedConnectionsField.setAccessible(true);

        int actualSize = ((Queue<Connection>) availableConnectionsField.get(connectionPool)).size() +
                ((Queue<Connection>) usedConnectionsField.get(connectionPool)).size();

        availableConnectionsField.setAccessible(false);
        usedConnectionsField.setAccessible(false);

        assertEquals(expectedSize, actualSize);
    }

    @AfterAll
    static void shutdown() throws NoSuchFieldException, IllegalAccessException {
        connectionPool.shutdown();
        Field field = ConnectionPoolImpl.class.getDeclaredField("availableConnections");
        field.setAccessible(true);
        int expectedSize = 0;
        @SuppressWarnings("unchecked")
        int actualSize = ((Queue<Connection>) field.get(connectionPool)).size();
        field.setAccessible(false);
        assertEquals(expectedSize, actualSize);
    }

}