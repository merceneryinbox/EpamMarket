import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnector {
    Connection conn = null;

    private void getcon() {
        try {
            ComboPooledDataSource instance = DataSourceInit.getDataSource();
            conn = instance.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
