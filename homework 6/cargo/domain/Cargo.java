package ru.epam.javacore.lesson_4.homework.cargo.domain;

import ru.epam.javacore.lesson_4.homework.common.domain.BaseEntity;
import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

public abstract class Cargo extends BaseEntity {
    protected String name;
    protected int weight;
    protected CargoType cargoType = setCargoType();
    protected Transportation[] transportations;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public CargoType setCargoType() {
        return null;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                '}';
    }
}
