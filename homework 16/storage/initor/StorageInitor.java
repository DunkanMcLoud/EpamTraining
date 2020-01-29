package ru.epam.javacore.lesson_16_concurrency.homework.storage.initor;

import ru.epam.javacore.lesson_16_concurrency.homework.cargo.exception.checked.CargoNotExistsEsception;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.exception.checked.CarrierNotExistsException;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, CargoNotExistsEsception, CarrierNotExistsException;
}
