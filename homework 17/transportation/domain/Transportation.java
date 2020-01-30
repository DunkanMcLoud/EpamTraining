package ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.domain.BaseEntity;

import java.util.Date;

public class Transportation extends BaseEntity {

  private Cargo cargo;
  private Carrier carrier;
  private String description;
  private String billTo;
  private Date transportationBeginDate;

  public Date getTransportationBeginDate() {
    return transportationBeginDate;
  }

  public void setTransportationBeginDate(Date transportationBeginDate) {
    this.transportationBeginDate = transportationBeginDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBillTo() {
    return billTo;
  }

  public void setBillTo(String billTo) {
    this.billTo = billTo;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public Carrier getCarrier() {
    return carrier;
  }

  public void setCarrier(Carrier carrier) {
    this.carrier = carrier;
  }

  @Override
  public String toString() {
    return "Transportation{" +
        "description='" + description + '\'' +
        ", Carrier='" + carrier.getName() + '\'' +
        ", Cargo=" + cargo.getName() +
        '}'+" Date="+"{"+transportationBeginDate+"}";
  }
}
