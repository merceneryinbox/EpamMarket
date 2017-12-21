package DbConnection;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    public static void init(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(getInitQuery())) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void drop(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(getDropQuery())) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getInitQuery() {
        try (InputStream goodssql = DatabaseManager.class.
                getResourceAsStream("/goods.sql");
             InputStream userssql = DatabaseManager.class.
                     getResourceAsStream("/users.sql");
             InputStream cartsql = DatabaseManager.class.
                     getResourceAsStream("/cart.sql")) {

            String query = getQueryFromInputStreams(userssql, goodssql, cartsql);
            return query;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDropQuery() {
        // Most likely works only for h2
        // TODO Shefer 19.12 : Now works only one of scripts
        return "DROP TABLE users, goods, cart";
    }

    /**
     * This methods collects all query files into one big query
     * Used to collect all table initializing queries
     */
    private static String getQueryFromInputStreams(InputStream... streams) throws IOException {
        StringBuffer strBuff = new StringBuffer();
        for (InputStream stream : streams) {
            InputStreamReader isr = new InputStreamReader(stream);
            BufferedReader buff = new BufferedReader(isr);
            int c;
            while ((c = buff.read()) != -1) {
                strBuff.append((char) c);
            }
        }
        return strBuff.toString();
    }

}
