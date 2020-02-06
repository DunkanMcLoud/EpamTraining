package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool implements ConnectionPoolInterface {
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    private ConnectionPool(String url, String user, String password, List<Connection> pool) {
     this.url=url;
     this.user=user;
     this.password=password;
     this.connectionPool=pool;
    }


    public static ConnectionPool create(String url, String user, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url,user,password));
        }
        return new ConnectionPool(url,user,password,pool);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize(){
        return connectionPool.size()+usedConnections.size();
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size()-1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void shutdown(){
        usedConnections.forEach(this::releaseConnection);
        connectionPool.forEach((c)-> {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.clear();
        });
    }
}
