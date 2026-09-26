package config;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
        private static final Properties props = new Properties();

        static {
            try (InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                props.load(input);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load db.properties", e);
            }
        }

        public static String get(String key) {
            return props.getProperty(key);
        }
    }

