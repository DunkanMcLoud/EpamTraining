package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection;

import java.sql.Connection;

public interface ConnectionPoolInterface {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getURL();
    String getUser();
    String getPassword();
    void shutdown();
}
