package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain;

import java.util.Date;

public class FoodCargo extends Cargo {

  private Date expirationDate;
  private int storeTemperature;

  @Override
  public CargoType getCargoType() {
    return CargoType.FOOD;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getStoreTemperature() {
    return storeTemperature;
  }

  public void setStoreTemperature(int storeTemperature) {
    this.storeTemperature = storeTemperature;
  }
}
