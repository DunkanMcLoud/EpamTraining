package ru.epam.javacore.lesson_4.homework.transportation.repo;

import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

public interface TransportationRepoInterface {
    Transportation getTransportationsByID(long id);
    Transportation[] getTransportationsByName(String name);
    void addTransportation(Transportation transportation);
    void deleteByID(long id);
}
