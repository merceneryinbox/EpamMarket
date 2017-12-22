package DbConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class JdbcConnector {
    final Properties properties = new Properties();

    private void getConnection() {
        try (InputStream resourceAsStream = JdbcConnector.class
                .getResourceAsStream("resources\\jdbc.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            log.error(
                    "Droped down at private void getConnection() in JdbcConnector because of \n" + e
                            .getMessage());
        }

        try {
            Class.forName(properties.getProperty("driver"), true,
                    ComboPooledDataSource.class.getClassLoader());
        } catch (Exception e) {
            log.error(
                    "Droped down at private void getConnection() in JdbcConnector because of \n" + e
                            .getMessage());
        }
    }
}
