package ru.epam.javacore.lesson_4.homework.cargo;

import ru.epam.javacore.lesson_4.homework.ID_generator;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

public abstract class Cargo {
    private Long id;
    private String name;
    private int weight;
    private CargoType cargoType = setCargoType();
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
