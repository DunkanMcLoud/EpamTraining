package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl;


import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.CollectionUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.IdGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.cargoArray;
import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.cargoIndex;

public class CargoArrayRepoImpl extends CommonCargoRepo {

    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public List<Cargo> findByName(String name) {
        Stream<Cargo> cargoStream = getAll().stream();
        return cargoStream.filter(cargo -> cargo.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }


    private Cargo[] excludeNullableElementsFromArray(Cargo[] cargos) {
        int sizeOfArrWithNotNullElems = 0;

        for (Cargo cargo : cargos) {
            if (cargo != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return EMPTY_CARGO_ARRAY;
        } else {
            Cargo[] result = new Cargo[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Cargo cargo : cargos) {
                if (cargo != null) {
                    result[index++] = cargo;
                }
            }

            return result;
        }
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
        for (Cargo cargo : cargoArray) {
            if (cargo != null && id != null && id.equals(cargo.getId())) {
                return Optional.of(cargo);
            }
        }

        return Optional.empty();
    }

    @Override
    public void save(Cargo cargo) {
        if (cargoIndex == cargoArray.length) {
            Cargo[] newCargos = new Cargo[cargoArray.length * 2];
            ArrayUtils.copyArray(cargoArray, newCargos);
            cargoArray = newCargos;
        }

        cargo.setId(IdGenerator.generateId());
        cargoArray[cargoIndex] = cargo;
        cargoIndex++;
    }

    @Override
    public boolean update(Cargo entity) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(cargoArray, id).orElse(null);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(cargoArray, indexToDelete);
            return true;
        }
    }

    @Override
    public List<Cargo> getAll() {
        Cargo[] cargos = excludeNullableElementsFromArray(cargoArray);
        return cargos.length == 0 ? Collections.emptyList() : Arrays.asList(cargoArray);
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

}
