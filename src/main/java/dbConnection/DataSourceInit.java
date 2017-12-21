package dbConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface DataSourceInit {
    ComboPooledDataSource instance = new ComboPooledDataSource();

     static DataSource getDataSource() {

            synchronized (DataSourceInit.class) {
                final Properties properties = new Properties();
                try (InputStream resourceAsStream = DataSourceInit.class.getResourceAsStream("/jdbc/jdbc.properties")) {
                    properties.load(resourceAsStream);
                    final String url = properties.getProperty("url");
                    final String userName = properties.getProperty("userName");
                    final String password = properties.getProperty("password");
                    final String driver = properties.getProperty("driver");
                    final int maxpoolsize = Integer.parseInt(properties.getProperty("pool.max"));
                    instance.setDriverClass(driver);
                    instance.setJdbcUrl(url);
                    instance.setUser(userName);
                    instance.setPassword(password);
                    instance.setMaxPoolSize(maxpoolsize);
                } catch (IOException | PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        return instance;
    }

}