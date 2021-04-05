package com.epam.util;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
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
            logger.info("Properties were loaded from file: {}", propertiesFileName);
        } catch (IOException e) {
            logger.error("Loading properties from file {} failed", propertiesFileName);
            e.printStackTrace();
        }
    }
}
