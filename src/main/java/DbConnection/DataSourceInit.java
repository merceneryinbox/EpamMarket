package DbConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface DataSourceInit {

    ComboPooledDataSource pgInstance = new ComboPooledDataSource();
    ComboPooledDataSource h2Instance = new ComboPooledDataSource();

    static DataSource getDataSource() {
        return getPostgres();
    }

    static DataSource getPostgres() {
        synchronized (DataSourceInit.class) {
            final Properties properties = new Properties();
            try (InputStream resourceAsStream = DataSourceInit.class.getResourceAsStream(
                    "/jdbc/jdbc.properties")) {
                properties.load(resourceAsStream);
                final String url         = properties.getProperty("url");
                final String userName    = properties.getProperty("userName");
                final String password    = properties.getProperty("password");
                final String driver      = properties.getProperty("driver");
                final int    maxpoolsize = Integer.parseInt(properties.getProperty("pool.max"));
                pgInstance.setDriverClass(driver);
                pgInstance.setJdbcUrl(url);
                pgInstance.setUser(userName);
                pgInstance.setPassword(password);
                pgInstance.setMaxPoolSize(maxpoolsize);
            } catch (IOException | PropertyVetoException e) {
                e.printStackTrace();
            }
        }
        return pgInstance;
    }

    static DataSource getH2() {
        synchronized (DataSourceInit.class) {
            final Properties properties = new Properties();
            try (InputStream resourceAsStream = DataSourceInit.class.getResourceAsStream(
                    "/jdbc/jdbc.properties")) {
                properties.load(resourceAsStream);
                final String url = "jdbc:h2:file:./src/main/resources/h2/epammarket";
                //jdbc:h2:mem:
                final String userName    = "";
                final String password    = "";
                final String driver      = "org.h2.Driver";
                final int    maxpoolsize = Integer.parseInt(properties.getProperty("pool.max"));
                h2Instance.setDriverClass(driver);
                h2Instance.setJdbcUrl(url);
                h2Instance.setUser(userName);
                h2Instance.setPassword(password);
                h2Instance.setMaxPoolSize(maxpoolsize);
            } catch (IOException | PropertyVetoException e) {
                e.printStackTrace();
            }
        }
        return h2Instance;
    }

}