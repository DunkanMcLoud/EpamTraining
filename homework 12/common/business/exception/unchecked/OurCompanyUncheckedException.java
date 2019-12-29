package ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.exception.unchecked;

public class OurCompanyUncheckedException extends RuntimeException {

  public OurCompanyUncheckedException(String message) {
    super(message);
  }
}
