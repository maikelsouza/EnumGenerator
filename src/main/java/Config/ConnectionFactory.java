package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {

//        String url = "jdbc:mysql://localhost:3306/condvirtuais2"; //Nome da base de dados
//        String user = "root"; //nome do usuário do MySQL
//        String password = "root"; //senha do MySQL



        String url = "jdbc:oracle:thin:@//scan-sdfdevxv.pf.gov.br:1521/dpf06ta"; //Nome da base de dados
        String user = "AGESPUSR"; //nome do usuário do MySQL
        String password = "AGESPUSR"; //senha do MySQL
        // TODO: VERIFCAR ONDE CONFIGURAR O SCHEMA

        return DriverManager.getConnection(url, user, password);
    }
}
