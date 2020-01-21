package ru.epam.javacore.lesson_14_serialization.homework.storage.initor;

import ru.epam.javacore.lesson_14_serialization.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
