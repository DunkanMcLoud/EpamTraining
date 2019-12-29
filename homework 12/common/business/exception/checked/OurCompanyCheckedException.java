package ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.exception.checked;

public class OurCompanyCheckedException extends Exception {

  public OurCompanyCheckedException(String message) {
    super(message);
  }

  public OurCompanyCheckedException(String message, Exception cause) {
    super(message);
    this.initCause(cause);
  }
}
