package ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.DataSource;

import java.io.IOException;
import java.sql.SQLException;

public class SQLStorageInitor implements StorageInitor {
    @Override
    public void initStorage() throws InitStorageException, IOException, SQLException {
        DataSource.initDataSource();
        DataSource.getConnection().createStatement().executeUpdate(SQLUtils.getStringFromSQLScript("src/main/resources/ru/epam/javacore/lesson_23_relational_db/db_config/create-schema.sql"));
        DataSource.getConnection().createStatement().execute(SQLUtils.getStringFromSQLScript("src/main/resources/ru/epam/javacore/lesson_23_relational_db/db_config/init-data.sql"));
    }
}
