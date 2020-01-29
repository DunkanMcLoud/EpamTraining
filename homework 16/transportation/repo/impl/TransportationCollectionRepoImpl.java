package ru.epam.javacore.lesson_16_concurrency.homework.transportation.repo.impl;


import ru.epam.javacore.lesson_16_concurrency.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.lesson_16_concurrency.homework.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void save(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Optional<Transportation> findById(Long id) {
        Optional<Transportation> result = Optional.ofNullable(null);
        for (Transportation transportation : transportationCollection) {
            if (transportation.getId().equals(id)) {
                return Optional.of(transportation);
            }
        }
        return result;
    }

    @Override
    public List<Transportation> getAll() {
        return transportationCollection;
    }

    @Override
    public boolean update(Transportation transportation) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleted = false;

        Iterator<Transportation> iter = transportationCollection.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    @Override
    public int countAll() {
        return transportationCollection.size();
    }
}
