package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection;

import java.sql.SQLException;

public class ConnectionPoolHandler {
    private static String URL = "jdbc:h2:~/homework24";
    private static String USER= "lol";
    private static String PASSWORD = "";
    private static ConnectionPool connectionPool;

    static {
        try {
            connectionPool = ConnectionPool.create(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.forEach((exception)->exception.printStackTrace());
        }
    }


    public static ConnectionPool getConnectionPool(){
        return connectionPool;
    };

}
