package utils;
import dataProviders.ConfigFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class PropertyUtils {
    private static final Logger log = LogManager.getLogger(PropertyUtils.class);

    public static String getPropertyFromConfigFile(String key) {
        ConfigFileReader configFileReader = new ConfigFileReader();
        Properties properties = configFileReader.initProperties();
        String value = properties.getProperty(key);
        if (value != null) {
            log.debug("Property '{}' retrieved successfully with value: '{}'", key, value);
        } else {
            log.debug("Property '{}' not found in the configuration file.", key);
        }
        return value;
    }
}
