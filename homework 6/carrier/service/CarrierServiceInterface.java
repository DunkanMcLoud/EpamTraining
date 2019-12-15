package ru.epam.javacore.lesson_4.homework.carrier.service;

import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;

public interface CarrierServiceInterface {
    Carrier getByID(long id);
    void addCarrier(Carrier cargo);
    Carrier[] findByName(String name);
    void deleteByID(long id);

}
