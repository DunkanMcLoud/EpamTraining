package ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.service;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Optional;

public class TransportationServiceImpl implements TransportationService {

  private TransportationRepo transportationRepo;

  public TransportationServiceImpl(
      TransportationRepo transportationRepo) {
    this.transportationRepo = transportationRepo;
  }

  @Override
  public boolean deleteById(Long id) {
    return transportationRepo.deleteById(id);
  }

  @Override
  public void printAll() {
    List<Transportation> allTransportations = transportationRepo.getAll();
    for (Transportation transportation : allTransportations) {
      System.out.println(transportation);
    }
  }

  @Override
  public void save(Transportation transportation) {
    transportationRepo.save(transportation);
  }

  @Override
  public Optional<Transportation> findById(Long id) {
    return transportationRepo.findById(id);
  }

  @Override
  public List<Transportation> getAll() {
    return transportationRepo.getAll();
  }

  @Override
  public boolean update(Transportation transportation) {
    if (transportation != null) {
      return transportationRepo.update(transportation);
    }

    return false;
  }

  @Override
  public int countAll() {
    return transportationRepo.countAll();
  }
}
