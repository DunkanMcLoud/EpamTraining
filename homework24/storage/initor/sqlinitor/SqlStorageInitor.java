package ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor.sqlinitor;


import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.ConnectionPool;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.ConnectionPoolHandler;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.handlers.cargo.table.TableCreator;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor.StorageInitor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlStorageInitor implements StorageInitor {
    @Override
    public void initStorage() throws InitStorageException {
        ConnectionPool connectionPool = ConnectionPoolHandler.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
            Statement statementForCargos = connection.createStatement();) {
            statementForCargos.executeUpdate(TableCreator.getCargoSchema());
        } catch (SQLException ex) {
            ex.forEach((exception)->exception.printStackTrace());
        }
    }
}
