package ru.epam.javacore.lesson_4.homework.cargo;

public class CargoComputers extends Cargo {
    @Override
    public CargoType setCargoType() {
        return CargoType.COMPUTERS;
    }
}
