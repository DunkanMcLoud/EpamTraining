package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.unchecked;

public class OurCompanyUncheckedException extends RuntimeException {

  public OurCompanyUncheckedException(String message) {
    super(message);
  }
}
