package ru.epam.javacore.lesson_4.homework.transportation.repo;

import ru.epam.javacore.lesson_4.homework.storage.Storage;
import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

public class TransportationRepo implements TransportationRepoInterface {
    @Override
    public Transportation getTransportationsByID(long id) {
        return Storage.getTransportationByID(id);
    }

    @Override
    public Transportation[] getTransportationsByName(String name) {
        return (Transportation[]) Storage.getTransportationsByName(name);
    }

    @Override
    public void addTransportation(Transportation transportation) {
        Storage.addTransportation(transportation);
    }

    @Override
    public void deleteByID(long id) {
        Storage.deleteTransportationByID(id);
    }
}
