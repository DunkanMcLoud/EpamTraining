package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'ru/epam/javacore/lesson_23_relational_db/db_migrations/create-schema.sql'");
        config.setUsername("lol");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }


    private DataSource() {
    }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
