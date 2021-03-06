package ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.checked.InitStorageException;

import java.io.IOException;
import java.sql.SQLException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, IOException, SQLException;
}
