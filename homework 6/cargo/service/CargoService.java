package ru.epam.javacore.lesson_4.homework.cargo.service;

import ru.epam.javacore.lesson_4.homework.cargo.domain.Cargo;

public class CargoService implements CargoServiceInterface{
    @Override
    public Cargo getByID(long id) {
        return null;
    }

    @Override
    public void addCargo(Cargo cargo) {

    }

    @Override
    public Cargo[] findByName(String name) {
        return new Cargo[0];
    }

    @Override
    public void deleteByID(long id) {

    }
}
