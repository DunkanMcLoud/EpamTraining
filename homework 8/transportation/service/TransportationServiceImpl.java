package ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.repo.TransportationRepo;

import java.util.List;

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
  public void add(Transportation transportation) {
    transportationRepo.add(transportation);
  }

  @Override
  public Transportation getById(Long id) {
    return transportationRepo.getById(id);
  }

  @Override
  public List<Transportation> getAll() {
    return transportationRepo.getAll();
  }

  @Override
  public void updateTransportationByID(Long id, Transportation transportation) {
    boolean isFound = transportationRepo.deleteById(id);
    if (isFound) {
      if ( transportation == null) {
        System.out.println("Transportation Successfully deleted");
      } else {
        transportationRepo.add(transportation);
      }
    } else {
      System.out.println("Not able to find transportation to update. It does not exist");
    }
  }

  @Override
  public void updateAllTransportations(List<Transportation> list) {
    transportationRepo.updateAllTransportations(list);
  }
}
