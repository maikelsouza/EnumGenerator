package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection createConnection() throws Exception {


        String url = "jdbc:oracle:thin:@//XYZ:1521/ABC";
        String user = "XXXXXX";
        String password = "XXXXXX";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new Exception("Error to generate enum: Is not possible to connect with the database");
        }
    }
}
