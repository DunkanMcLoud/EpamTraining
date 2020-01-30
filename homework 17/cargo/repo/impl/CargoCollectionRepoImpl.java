package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl;


import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.CollectionUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.IdGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.epam.javacore.lesson_18_19_20_java_8.homework.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

  @Override
  public Optional<Cargo> getByIdFetchingTransportations(long id) {
    return findById(id);
  }

  @Override
  public List<Cargo> findByName(String name) {
    Stream<Cargo> cargoStream = cargoCollection.stream();
    return cargoStream.filter((cargo -> cargo.getName().equalsIgnoreCase(name))).collect(Collectors.toList());
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
    for (Cargo cargo : cargoCollection) {
      if (id != null && id.equals(cargo.getId())) {
        return Optional.of(cargo);
      }
    }
    return Optional.empty();
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
