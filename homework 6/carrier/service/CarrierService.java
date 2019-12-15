package ru.epam.javacore.lesson_4.homework.carrier.service;

import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;

public class CarrierService implements CarrierServiceInterface {
    @Override
    public Carrier getByID(long id) {
        return null;
    }

    @Override
    public void addCarrier(Carrier cargo) {

    }

    @Override
    public Carrier[] findByName(String name) {
        return new Carrier[0];
    }

    @Override
    public void deleteByID(long id) {

    }
}
