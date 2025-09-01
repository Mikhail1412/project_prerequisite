package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // set up a database connection
    private static final String URL  = "jdbc:mysql://localhost:3306/task_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "appuser";    // или root, если так подключаешься
    private static final String PASS = "твой_пароль";


    private Util() {}

    /** Верни живое соединение; обработку исключений делаем в DAO */
    public static Connection getConnection() throws SQLException {
        // при современном драйвере Class.forName не нужен
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
