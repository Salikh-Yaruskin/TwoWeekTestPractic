package helpers;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider instance;

    @Getter
    private final Properties properties = new Properties();

    public static synchronized PropertyProvider getInstance() {
        if (instance == null)
            instance = new PropertyProvider();
        return instance;
    }

    public PropertyProvider() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
