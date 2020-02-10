package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.CargoRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain.Transportation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CargoServiceImpl implements CargoService {

  private CargoRepo cargoRepo;

  public CargoServiceImpl(CargoRepo cargoRepo) {
    this.cargoRepo = cargoRepo;
  }

  @Override
  public void save(Cargo cargo) {
    cargoRepo.save(cargo);
  }

  @Override
  public Optional<Cargo> findById(Long id) {
    if (id != null) {
      return cargoRepo.findById(id);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Cargo> getByIdFetchingTransportations(Long id) {
    if (id != null) {
      return cargoRepo.getByIdFetchingTransportations(id);
    }
    return Optional.empty();
  }

  @Override
  public List<Cargo> getAll() throws SQLException {
    return cargoRepo.getAll();
  }

  @Override
  public int countAll() {
      try {
          return cargoRepo.countAll();
      } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException();
      }
  }

  @Override
  public List<Cargo> findByName(String name) {return cargoRepo.findByName(name);
  }

  @Override
  public boolean deleteById(Long id) {
    Optional<Cargo> cargoOptional = this.getByIdFetchingTransportations(id);

    if (cargoOptional.isPresent()) {
      List<Transportation> transportations = cargoOptional.get().getTransportations();
      boolean hasTransportations = transportations != null && transportations.size() > 0;
      if (hasTransportations) {
        throw new CargoDeleteConstraintViolationException(id);
      }

      return cargoRepo.deleteById(id);
    } else {
      return false;
    }
  }

  @Override
  public void printAll() throws SQLException {
    List<Cargo> allCargos = cargoRepo.getAll();

    for (Cargo cargo : allCargos) {
      System.out.println(cargo);
    }
  }

  @Override
  public boolean update(Cargo cargo) {
    if (cargo != null) {
      return cargoRepo.update(cargo);
    }

    return false;
  }

  @Override
  public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
    return cargoRepo.search(cargoSearchCondition);
  }
}
