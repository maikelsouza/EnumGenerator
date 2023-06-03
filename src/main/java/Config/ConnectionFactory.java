package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/condvirtuais2"; //Nome da base de dados
        String user = "root"; //nome do usuário do MySQL
        String password = "root"; //senha do MySQL

        return DriverManager.getConnection(url, user, password);
    }
}
