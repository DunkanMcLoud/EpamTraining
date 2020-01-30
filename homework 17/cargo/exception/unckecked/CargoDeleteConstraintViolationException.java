package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.exception.unckecked;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CargoDeleteConstraintViolationException extends OurCompanyUncheckedException {

  private static final String MESSAGE = "Cant delete cargo with id '%s'. There are transportations which relates to it!";

  public CargoDeleteConstraintViolationException(String message) {
    super(message);
  }

  public CargoDeleteConstraintViolationException(long cargoId) {
    this(String.format(MESSAGE, cargoId));
  }
}
