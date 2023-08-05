package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection createConnection() throws Exception {

        String url = "jdbc:oracle:thin:@//xxxxxx:1521/yyyyyy";
        String user = "user";
        String password = "password";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new Exception("Error to generate enum: Is not possible to connect with the database");
        }
    }
}
