package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection createConnection() throws Exception {


        String url = "jdbc:oracle:thin:@//scan-sdfdevxv.pf.gov.br:1521/dpf06ta";
        String user = "ATURMAUSR";
        String password = "ATURMAUSR";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new Exception("Error to generate enum: Is not possible to connect with the database");
        }
    }
}
