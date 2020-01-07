package ru.epam.javacore.lesson_12_io_nio.homework.carrier.service;

import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_12_io_nio.homework.carrier.exception.unchecked.CarrierDeleteConstraintViolationException;
import ru.epam.javacore.lesson_12_io_nio.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

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
  public Carrier findById(Long id) {
    if (id != null) {
      return carrierRepo.findById(id);
    }

    return null;
  }

  @Override
  public Carrier getByIdFetchingTransportations(Long id) {
    if (id != null) {
      return carrierRepo.getByIdFetchingTransportations(id);
    }

    return null;
  }

  @Override
  public List<Carrier> findByName(String name) {
    Carrier[] found = carrierRepo.findByName(name);

    if (found == null || found.length == 0){
        return null;
    }else {
        return Arrays.asList(found);
    }
  }

  @Override
  public List<Carrier> getAll() {
    return carrierRepo.getAll();
  }

  @Override
  public int countAll() {
    return this.carrierRepo.countAll();
  }

  @Override
  public boolean deleteById(Long id) {
    Carrier carrier = this.getByIdFetchingTransportations(id);

    if (carrier != null) {
      List<Transportation> transportations = carrier.getTransportations();
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
  public void printAll() {
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
