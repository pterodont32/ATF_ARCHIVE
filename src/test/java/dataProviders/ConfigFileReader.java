package dataProviders;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    public static final Logger logger =  LogManager.getLogger(ConfigFileReader.class);
    private final String propertyFilePath = "src/test/resources/config/configuration.properties";

    public Properties initProperties() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyFilePath));
            logger.debug("Properties loaded successfully from {}", propertyFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to load properties from {}: {}", propertyFilePath, e.getMessage());
        }
        return properties;
    }

}
