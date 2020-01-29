package ru.epam.javacore.lesson_16_concurrency.homework.carrier.repo.impl;


import ru.epam.javacore.lesson_16_concurrency.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.exception.checked.CarrierNotExistsException;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_16_concurrency.homework.storage.IdGenerator;

import java.util.*;

import static ru.epam.javacore.lesson_16_concurrency.homework.storage.Storage.carrierCollection;

public class CarrierCollectionRepoImpl implements CarrierRepo {

  @Override
  public void save(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    carrierCollection.add(carrier);
  }

  @Override
  public Optional<Carrier> findById(Long id) {
      Optional<Carrier> result = Optional.ofNullable(null);
      for (Carrier carrier : carrierCollection) {
      if (carrier.getId().equals(id)) {
        return Optional.of(carrier);
      }}
      return result;
    }


  @Override
  public Carrier getByIdFetchingTransportations(long id) throws CarrierNotExistsException {
    if (findById(id).isPresent()) return findById(id).get();
    else throw new CarrierNotExistsException();
  }

  @Override
  public Carrier[] findByName(String name) {
    List<Carrier> result = new ArrayList<>();

    for (Carrier carrier : carrierCollection) {
      if (Objects.equals(carrier.getName(), name)) {
        result.add(carrier);
      }
    }

    return result.toArray(new Carrier[0]);
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
