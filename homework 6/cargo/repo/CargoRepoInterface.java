package ru.epam.javacore.lesson_4.homework.cargo.repo;

import ru.epam.javacore.lesson_4.homework.cargo.domain.Cargo;

public interface CargoRepoInterface {

    Cargo getByID(long id);
    void addCargo(Cargo cargo);
    Cargo[] findByName(String name);
    void deleteByID(long id);

}
