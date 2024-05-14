package utils;
import dataProviders.ConfigFileReader;
import java.util.Properties;

public class PropertyUtils {
    public static String getPropertyFromConfigFile(String key) {
        ConfigFileReader configFileReader = new ConfigFileReader();
        Properties properties = configFileReader.initProperties();
        return properties.getProperty(key);
    }
}
