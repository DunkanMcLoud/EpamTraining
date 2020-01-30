package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.impl;


import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.IdGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.carrierCollection;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void save(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierCollection.add(carrier);
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        for (Carrier carrier : carrierCollection) {
            if (carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public List<Carrier> findByName(String name) {
        Stream<Carrier> carrierStream = getAll().stream();
        return carrierStream.filter(carrier -> carrier.getName().equalsIgnoreCase(name)).collect(Collectors.toList());

    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<Carrier> iter = carrierCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
    }

    @Override
    public List<Carrier> getAll() {
        return carrierCollection;
    }

    @Override
    public int countAll() {
        return carrierCollection.size();
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
    }

}
