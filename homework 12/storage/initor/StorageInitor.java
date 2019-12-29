package ru.epam.javacore.lesson_11_generics_and_io.homework.storage.initor;

import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
