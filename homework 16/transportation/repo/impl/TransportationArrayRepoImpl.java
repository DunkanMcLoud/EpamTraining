package ru.epam.javacore.lesson_16_concurrency.homework.transportation.repo.impl;


import ru.epam.javacore.lesson_16_concurrency.homework.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.lesson_16_concurrency.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.epam.javacore.lesson_16_concurrency.homework.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static ru.epam.javacore.lesson_16_concurrency.homework.storage.Storage.transportationArray;
import static ru.epam.javacore.lesson_16_concurrency.homework.storage.Storage.transportationIndex;

public class TransportationArrayRepoImpl implements TransportationRepo {

  private static final Transportation[] EMPTY_TRANSPORTATION_ARRAY = new Transportation[0];

  @Override
  public void save(Transportation transportation) {
    if (transportationIndex == transportationArray.length) {
      Transportation[] newTransportations =
          new Transportation[transportationArray.length * 2];
      ArrayUtils.copyArray(transportationArray, newTransportations);
      transportationArray = newTransportations;
    }

    transportation.setId(IdGenerator.generateId());
    transportationArray[transportationIndex] = transportation;
    transportationIndex++;
  }

  @Override
  public Optional<Transportation> findById(Long id) {
      Optional<Transportation> result = Optional.ofNullable(null);
    for (Transportation transportation : transportationArray) {
      if (transportation != null && transportation.getId().equals(id)) {
        return Optional.of(transportation);
      }
    }
    return result;
  }

  @Override
  public List<Transportation> getAll() {
    Transportation[] transportations = excludeNullableElementsFromArray(transportationArray);
    return transportations.length == 0 ? Collections.emptyList()
        : Arrays.asList(transportationArray);
  }

  @Override
  public int countAll() {
    return getAll().size();
  }

  private Transportation[] excludeNullableElementsFromArray(Transportation[] transportations) {
    int sizeOfArrWithNotNullElems = 0;

    for (Transportation transportation : transportations) {
      if (transportation != null) {
        sizeOfArrWithNotNullElems++;
      }
    }

    if (sizeOfArrWithNotNullElems == 0) {
      return EMPTY_TRANSPORTATION_ARRAY;
    } else {
      Transportation[] result = new Transportation[sizeOfArrWithNotNullElems];
      int index = 0;
      for (Transportation transportation : transportations) {
        if (transportation != null) {
          result[index++] = transportation;
        }
      }

      return result;
    }
  }


  @Override
  public boolean update(Transportation transportation) {
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    Integer indexToDelete = findEntityIndexInArrayStorageById(transportationArray, id);

    if (indexToDelete == null) {
      return false;
    } else {
      ArrayUtils.removeElement(transportationArray, indexToDelete);
      return true;
    }
  }
}
