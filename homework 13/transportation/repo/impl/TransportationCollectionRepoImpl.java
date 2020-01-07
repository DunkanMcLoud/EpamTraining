package ru.epam.javacore.lesson_12_io_nio.homework.transportation.repo.impl;


import ru.epam.javacore.lesson_12_io_nio.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static ru.epam.javacore.lesson_12_io_nio.homework.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

  @Override
  public void save(Transportation transportation) {
    transportation.setId(IdGenerator.generateId());
    transportationCollection.add(transportation);
  }

  @Override
  public Transportation findById(Long id) {
    for (Transportation transportation : transportationCollection) {
      if (transportation.getId().equals(id)) {
        return transportation;
      }
    }

    return null;
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
