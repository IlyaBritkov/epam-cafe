package com.epam.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public final class PropertyReader {
    @Getter
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private PropertyReader() {
    }

    public static void loadProperties() {
        final String propertiesFileName = "dbConfiguration.properties";
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(inputStream);
            log.info("Properties were loaded from file: {}", propertiesFileName);
        } catch (IOException e) {
            log.error("Loading properties from file {} failed", propertiesFileName);
            e.printStackTrace();
        }
    }
}
