import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DataSourceInit {
    private static ComboPooledDataSource instance;

    public static ComboPooledDataSource getDataSource() {
        if (instance == null) {
            synchronized (JdbcConnector.class) {
                final Properties properties = new Properties();
                try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/jdbc.properties")) {
                    properties.load(resourceAsStream);
                    final String url = properties.getProperty("url");
                    final String userName = properties.getProperty("userName");
                    final String password = properties.getProperty("password");
                    final String driver = properties.getProperty("driver");
                    instance = new ComboPooledDataSource();
                    instance.setDriverClass(driver);
                    instance.setJdbcUrl(url);
                    instance.setUser(userName);
                    instance.setPassword(password);
                    instance.setMaxPoolSize(50);
                } catch (IOException | PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

}