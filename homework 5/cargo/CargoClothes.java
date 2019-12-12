package ru.epam.javacore.lesson_4.homework.cargo;

public class CargoClothes extends Cargo {
    @Override
    public CargoType setCargoType() {
        return CargoType.CLOTHES;
    }
}
