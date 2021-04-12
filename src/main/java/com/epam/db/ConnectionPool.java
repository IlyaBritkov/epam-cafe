package com.epam.db;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    boolean releaseConnection(Connection connection);

    String getURL();

    String getUSER();

    String getPASSWORD();

    int getSize();

    void shutdown();
}
