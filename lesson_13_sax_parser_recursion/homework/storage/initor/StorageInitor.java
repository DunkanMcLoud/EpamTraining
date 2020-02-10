package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
