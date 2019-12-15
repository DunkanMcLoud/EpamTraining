package ru.epam.javacore.lesson_4.homework.carrier.repo;

import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_4.homework.carrier.service.CarrierServiceInterface;
import ru.epam.javacore.lesson_4.homework.storage.Storage;

public class CarrierRepo implements CarrierServiceInterface {
    @Override
    public Carrier getByID(long id) {
        return Storage.getCarrierByID(id);
    }

    @Override
    public void addCarrier(Carrier carrier) {
        Storage.addCarrier(carrier);
    }

    @Override
    public Carrier[] findByName(String name) {
        return (Carrier[]) Storage.getCarrierByName(name);
    }

    @Override
    public void deleteByID(long id) {
        Storage.deleteCarrierByID(id);
    }
}
