package ru.epam.javacore.lesson_10_generics.homework.carrier.exception.unchecked;

import ru.epam.javacore.lesson_10_generics.homework.common.business.exception.unchecked.OurCompanyException;

public class CarrierDeleteConstraintViolationException extends OurCompanyException {

  private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

  public CarrierDeleteConstraintViolationException(String message) {
    super(message);
  }

  public CarrierDeleteConstraintViolationException(long carrierId) {
    this(String.format(MESSAGE, carrierId));
  }
}
