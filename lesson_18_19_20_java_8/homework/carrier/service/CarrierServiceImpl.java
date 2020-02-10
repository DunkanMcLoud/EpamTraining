package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.service;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.exception.unchecked.CarrierDeleteConstraintViolationException;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain.Transportation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarrierServiceImpl implements CarrierService {

  private CarrierRepo carrierRepo;

  public CarrierServiceImpl(
      CarrierRepo carrierRepo) {
    this.carrierRepo = carrierRepo;
  }

  @Override
  public void save(Carrier carrier) {
    carrierRepo.save(carrier);
  }

  @Override
  public Optional<Carrier> findById(Long id) {
    if (id != null) {
      return carrierRepo.findById(id);
    }

    return Optional.empty();
  }

  @Override
  public Optional<Carrier> getByIdFetchingTransportations(Long id) {
    if (id != null) {
      return (carrierRepo.getByIdFetchingTransportations(id));
    }else{
    return Optional.empty();
  }}

  @Override
  public List<Carrier> findByName(String name) {
   return carrierRepo.findByName(name);
  }

  @Override
  public List<Carrier> getAll() throws SQLException {
    return carrierRepo.getAll();
  }

  @Override
  public int countAll() throws SQLException {
    return this.carrierRepo.countAll();
  }

  @Override
  public boolean deleteById(Long id) {
    Optional<Carrier> carrier = this.getByIdFetchingTransportations(id);

    if (carrier.isPresent()) {
      List<Transportation> transportations = carrier.get().getTransportations();
      boolean hasTransportations = transportations != null && transportations.size() > 0;
      if (hasTransportations) {
        throw new CarrierDeleteConstraintViolationException(id);
      }

      return carrierRepo.deleteById(id);
    } else {
      return false;
    }
  }

  @Override
  public void printAll() throws SQLException {
    List<Carrier> carriers = carrierRepo.getAll();
    for (Carrier carrier : carriers) {
      System.out.println(carrier);
    }
  }

  @Override
  public boolean update(Carrier carrier) {
    if (carrier != null) {
      carrierRepo.update(carrier);
    }

    return false;
  }
}
