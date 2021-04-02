package com.epam.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection() throws SQLException;

    boolean releaseConnection(Connection connection);

    String getURL();

    String getUSER();

    String getPASSWORD();

    int getSize();

    void shutdown();
}
