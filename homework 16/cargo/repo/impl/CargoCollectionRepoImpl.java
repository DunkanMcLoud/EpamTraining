package ru.epam.javacore.lesson_16_concurrency.homework.cargo.repo.impl;


import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_16_concurrency.homework.common.solutions.utils.CollectionUtils;
import ru.epam.javacore.lesson_16_concurrency.homework.storage.IdGenerator;

import java.util.*;

import static ru.epam.javacore.lesson_16_concurrency.homework.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        Optional<Cargo> result = findById(id);
        return result;
    }

    @Override
    public Cargo[] findByName(String name) {
        List<Cargo> result = new ArrayList<>();

        for (Cargo carrier : cargoCollection) {
            if (Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        }

        return result.toArray(new Cargo[0]);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        List<Cargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchCondition.needSorting()) {
                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
            }
        }

        return cargos;
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        Optional<Cargo> result = Optional.ofNullable(null);
        for (Cargo cargo : cargoCollection) {
               if (id != null && id.equals(cargo.getId())) {
                result = Optional.ofNullable(cargo);
            }
        }
        return result;
    }



    @Override
    public void save(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargoCollection.add(cargo);
    }

    @Override
    public boolean update(Cargo entity) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<Cargo> iter = cargoCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (id != null && id.equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoCollection;
    }

    @Override
    public int countAll() {
        return cargoCollection.size();
    }


}
