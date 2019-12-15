package ru.epam.javacore.lesson_4.homework.transportation.service;

import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

public class TransportationService implements TransportationServiceInterface {
    @Override
    public Transportation getTransportationsByID(long id) {
        return null;
    }

    @Override
    public Transportation[] getTransportationsByName(String name) {
        return new Transportation[0];
    }

    @Override
    public void addTransportation(Transportation transportation) {

    }

    @Override
    public void deleteByID(long id) {

    }
}
