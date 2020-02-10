package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.exception.checked;

public class OurCompanyCheckedException extends Exception {

  public OurCompanyCheckedException(String message) {
    super(message);
  }

  public OurCompanyCheckedException(String message, Exception cause) {
    super(message);
    this.initCause(cause);
  }
}
