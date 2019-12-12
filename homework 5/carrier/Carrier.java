package ru.epam.javacore.lesson_4.homework.carrier;

import ru.epam.javacore.lesson_4.homework.ID_generator;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

import java.util.Arrays;

public class Carrier {
    private Long id;
    private String name;
    private String address;
    private CarrierType carrierType;
    private Transportation[] transportations;

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = ID_generator.generateID();
    }

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
