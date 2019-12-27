package ru.epam.javacore.lesson_10_generics.homework.common.business.exception.unchecked;

public class OurCompanyException extends RuntimeException {

  public OurCompanyException(String message) {
    super(message);
  }
}
