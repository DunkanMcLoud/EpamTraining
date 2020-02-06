package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.impl;


import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.IdGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.carrierArray;
import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.carrierIndex;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void save(Carrier carrier) {
        if (carrierIndex == carrierArray.length) {
            Carrier[] newCarriers = new Carrier[carrierArray.length * 2];
            ArrayUtils.copyArray(carrierArray, newCarriers);
            carrierArray = newCarriers;
        }

        carrier.setId(IdGenerator.generateId());
        carrierArray[carrierIndex] = carrier;
        carrierIndex++;
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
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


    private Carrier[] excludeNullableElementsFromArray(Carrier[] carriers) {
        int sizeOfArrWithNotNullElems = 0;

        for (Carrier carrier : carriers) {
            if (carrier != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            Carrier[] result = new Carrier[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Carrier carrier : carriers) {
                if (carrier != null) {
                    result[index++] = carrier;
                }
            }

            return result;
        }
    }

    @Override
    public List<Carrier> getAll() {
        Carrier[] carriers = excludeNullableElementsFromArray(carrierArray);
        return carriers.length == 0 ? Collections.emptyList() : Arrays.asList(carrierArray);
    }

    @Override
    public int countAll() {
        return this.getAll().size();
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        for (Carrier carrier : carrierArray) {
            if (carrier != null && carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Integer> indexToDeleteOptional = findEntityIndexInArrayStorageById(carrierArray, id);

        return indexToDeleteOptional.map(i -> {
            ArrayUtils.removeElement(carrierArray, i);
            return true;
        }).orElse(false);
    }
}
