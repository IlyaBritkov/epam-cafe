package com.epam.db;

import com.epam.util.PropertyReader;
import lombok.Getter;

import java.util.Objects;

/**
 * Singleton
 **/
@Getter
public final class DBConfiguration {
    private static volatile DBConfiguration INSTANCE;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int INITIAL_POOL_SIZE;
    private final int MAX_POOL_SIZE;
    private final int MAX_TIMEOUT_VALIDATE_CONNECTION;

    private DBConfiguration() {
        this.URL = Objects.requireNonNull(PropertyReader.getProperties().getProperty("url"));
        this.USER = Objects.requireNonNull(PropertyReader.getProperties().getProperty("user"));
        this.PASSWORD = Objects.requireNonNull(PropertyReader.getProperties().getProperty("password"));
        this.INITIAL_POOL_SIZE = Integer.parseInt(Objects.requireNonNull(PropertyReader.getProperties().getProperty("initialPoolSize")));
        this.MAX_POOL_SIZE = Integer.parseInt(Objects.requireNonNull(PropertyReader.getProperties().getProperty("maxPoolSize")));
        this.MAX_TIMEOUT_VALIDATE_CONNECTION = Integer.parseInt(Objects.requireNonNull(PropertyReader.getProperties().getProperty("maxTimeoutValidateConnection")));
    }

    public static DBConfiguration getInstance() {
        DBConfiguration localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (DBConfiguration.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new DBConfiguration();
                }
            }
        }
        return localInstance;
    }
}
