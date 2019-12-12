package ru.epam.javacore.lesson_4.homework.cargo;

public class CargoFood extends Cargo {
    @Override
    public CargoType setCargoType() {
        return CargoType.FOOD;
    }

}
