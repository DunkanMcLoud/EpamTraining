package ru.epam.javacore.lesson_18_19_20_java_8.homework.application;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl.CargoDatabaseRepoImplementation;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoDatabaseServiceImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor.InitStorageType;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor.StorageInitor;

import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.initor.StorageInitorFactory.getStorageInitor;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService = new CargoDatabaseServiceImpl(new CargoDatabaseRepoImplementation());

    public static void main(String[] args) {
        StorageInitor storageInitor = getStorageInitor(InitStorageType.SQL);
        try {
            storageInitor.initStorage();
        } catch (InitStorageException e) {
            e.printStackTrace();
        }
    }
}



