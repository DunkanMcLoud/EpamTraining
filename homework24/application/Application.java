package ru.epam.javacore.lesson_18_19_20_java_8.homework.application;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl.CargoDatabaseRepoImplementation;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoServiceImpl;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService = new CargoServiceImpl(new CargoDatabaseRepoImplementation());

    public static void main(String[] args) {
        cargoService.printAll();
    }
}



