package DbConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class JdbcConnector {
    final Properties properties = new Properties();

    private void getcon() throws IOException {
        try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("resources\\jdbc.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(properties.getProperty("driver"), true, ComboPooledDataSource.class.getClassLoader());
        } catch (Exception e) {
            System.out.println(" error while instantiating JdbcDriver");
        }
    }
}
