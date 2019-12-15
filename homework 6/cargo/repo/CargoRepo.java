package ru.epam.javacore.lesson_4.homework.cargo.repo;

import ru.epam.javacore.lesson_4.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_4.homework.storage.Storage;

public class CargoRepo implements CargoRepoInterface {
    @Override
    public Cargo getByID(long id) {
        return Storage.getCargoByID(id);
    }

    @Override
    public void addCargo(Cargo cargo) {
        Storage.addCargo(cargo);
    }

    @Override
    public Cargo[] findByName(String name) {
        return (Cargo[]) Storage.getCargosByName(name);
    }

    @Override
    public void deleteByID(long id) {
        Storage.deleteCargoByID(id);
    }
}
