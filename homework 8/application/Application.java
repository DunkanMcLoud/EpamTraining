package ru.epam.javacore.lesson_7_collections_continue_map.homework.application;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.common.solutions.utils.CollectionUtils;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.storage.initor.InMemoryStorageInitor;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.storage.initor.StorageInitor;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.service.TransportationService;

public class Application {

  private static final String SEPARATOR = "--------------";
  private static CargoService cargoService;
  private static CarrierService carrierService;
  private static TransportationService transportationService;

  public static void main(String[] args) {
    ServiceHolder.initServiceHolder(StorageType.COLLECTION);
    cargoService = ServiceHolder.getInstance().getCargoService();
    carrierService = ServiceHolder.getInstance().getCarrierService();
    transportationService = ServiceHolder.getInstance().getTransportationService();

    StorageInitor storageInitor = new InMemoryStorageInitor();
    storageInitor.initStorage();

    //printStorageData();
    //doSearchOperations();
    doSortCargos();
  }

  private static void printStorageData() {
    System.out.println("ALL CARGOS");
    cargoService.printAll();
    printSeparator();

    System.out.println("ALL CARRIERS");
    carrierService.printAll();
    printSeparator();

    System.out.println("ALL TRANSPOORTATIONS");
    transportationService.printAll();
    printSeparator();
  }

  private static void doSearchOperations() {
    System.out.println("SEARCH CARGO BY ID = 1");
    System.out.println(cargoService.getById(1L));
    printSeparator();

    System.out.println("SEARCH CARRIER BY ID = 8");
    System.out.println(carrierService.getById(8L));
    printSeparator();

    System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
    CollectionUtils.printCollection(cargoService.getByName("Clothers_Name_1"));
    printSeparator();

    System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
    CollectionUtils.printCollection(carrierService.getByName("Carrier_Name"));
  }

  private static void printSeparator() {
    System.out.println(SEPARATOR);
  }

  private static void doSortCargos(){
    sortByNames();
    sortByWeight();
    sortByNamesAndWeight();
  }

  private static void sortByNamesAndWeight() {
    System.out.println("Sorting By Names and Weight in ASC");
    cargoService.sortCargosByNameAndWeight(CargoService.SortType.ASC);
    cargoService.printAll();
    printSeparator();
    System.out.println("Sorting By Weight and Names in Descending Order");
    cargoService.sortCargosByNameAndWeight(CargoService.SortType.DESC);
    cargoService.printAll();
    printSeparator();
  }

  private static void sortByWeight() {
    System.out.println("Sorting By Weight in Ascending Order");
    cargoService.sortCargosByWeight(CargoService.SortType.ASC);
    cargoService.printAll();
    printSeparator();
    System.out.println("Sorting By Weight in Descending Order");
    cargoService.sortCargosByWeight(CargoService.SortType.DESC);
    cargoService.printAll();
    printSeparator();
  }

  private static void sortByNames() {
    printSeparator();
    System.out.println("Sorting  By Names in ascending order");
    cargoService.sortCargosByName(CargoService.SortType.ASC);
    cargoService.printAll();
    printSeparator();
    System.out.println("Sorting By Names in Descending Order");
    cargoService.sortCargosByName(CargoService.SortType.DESC);
    cargoService.printAll();
    printSeparator();
  }

}
