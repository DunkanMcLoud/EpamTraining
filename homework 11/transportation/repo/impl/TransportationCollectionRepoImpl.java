package ru.epam.javacore.lesson_10_generics.homework.transportation.repo.impl;


import static ru.epam.javacore.lesson_10_generics.homework.storage.Storage.transportationCollection;

import ru.epam.javacore.lesson_10_generics.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_10_generics.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_10_generics.homework.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

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
