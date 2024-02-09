package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/kata";
    private static final String login = "root";
    private static final String password = "root";


    public Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Ошибка соединения с Data Base");
        }
        return connection;
    }
}
