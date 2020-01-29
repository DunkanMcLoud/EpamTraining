package ru.epam.javacore.lesson_16_concurrency.homework.common.business.exception.checked;

public class InitStorageException extends OurCompanyCheckedException {

  public InitStorageException(String message) {
    super(message);
  }
}
