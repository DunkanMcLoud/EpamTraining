package ru.epam.javacore.lesson_4.homework.transportation.service;

import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

public interface TransportationServiceInterface {
    Transportation getTransportationsByID(long id);
    Transportation[] getTransportationsByName(String name);
    void addTransportation(Transportation transportation);
    void deleteByID(long id);
}
