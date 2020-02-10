package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection;

import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static final HikariDataSource ds = new HikariDataSource();

    public static void initDataSource() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/ru/epam/javacore/lesson_23_relational_db/db_config/config.properties");
            properties.load(inputStream);
            Class.forName(properties.getProperty("custom.database.driver"));
            ds.setDriverClassName(properties.getProperty("custom.database.driver"));
            ds.setJdbcUrl(properties.getProperty("custom.database.url"));
            ds.setUsername(properties.getProperty("custom.database.user"));
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
