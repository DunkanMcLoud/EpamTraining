package ru.epam.javacore.lesson_4.homework.carrier.domain;

import ru.epam.javacore.lesson_4.homework.common.domain.BaseEntity;
import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

import java.util.Arrays;

public class Carrier extends BaseEntity {
    protected String name;
    protected String address;
    protected CarrierType carrierType;
    protected Transportation[] transportations;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                ", transportations=" + Arrays.toString(transportations) +
                '}';
    }
}
