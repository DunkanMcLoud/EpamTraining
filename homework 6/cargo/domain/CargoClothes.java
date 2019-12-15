package ru.epam.javacore.lesson_4.homework.cargo.domain;

public class CargoClothes extends Cargo {
    @Override
    public CargoType setCargoType() {
        return CargoType.CLOTHES;
    }
}
