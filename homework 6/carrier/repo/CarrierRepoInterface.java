package ru.epam.javacore.lesson_4.homework.carrier.repo;

import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;

public interface CarrierRepoInterface {
    Carrier getByID(long id);
    void addCarrier(Carrier carrier);
    Carrier[] findByName(String name);
    void deleteByID(long id);

}
