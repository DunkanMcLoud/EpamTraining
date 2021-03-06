package ru.epam.javacore.lesson_4.homework.transportation.domain;

import ru.epam.javacore.lesson_4.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_4.homework.common.domain.BaseEntity;

import java.util.Date;

public class Transportation extends BaseEntity {
    protected Cargo cargo;
    protected Carrier carrier;
    protected String description;
    protected String billTo;
    protected Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                "id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", date=" + date +
                '}';
    }
}
