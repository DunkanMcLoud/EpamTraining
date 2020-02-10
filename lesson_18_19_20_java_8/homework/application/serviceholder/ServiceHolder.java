package ru.epam.javacore.lesson_18_19_20_java_8.homework.application.serviceholder;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl.CargoSQLRepoImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoServiceImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.impl.CarrierSQLRepoImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.service.CarrierServiceImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.repo.impl.TransportationSQLRepoImpl;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.service.TransportationService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {

  private static ServiceHolder instance = null;

  private final CarrierService carrierService;
  private final CargoService cargoService;
  private final TransportationService transportationService;

  private ServiceHolder(StorageType storageType) {
    SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
    cargoService = initedServiceHolder.cargoService;
    carrierService = initedServiceHolder.carrierService;
    transportationService = initedServiceHolder.transportationService;
  }

  public static void initServiceHolder(StorageType storageType) {
    ServiceHolder.instance = new ServiceHolder(storageType);
  }

  public static ServiceHolder getInstance() {
    return instance;
  }

  private static class SimpleServiceHolder {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public SimpleServiceHolder(
        CarrierService carrierService,
        CargoService cargoService,
        TransportationService transportationService) {
      this.carrierService = carrierService;
      this.cargoService = cargoService;
      this.transportationService = transportationService;
    }
  }

  private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
    switch (storageType) {


        case SQL:
            return new SimpleServiceHolder(
                    new CarrierServiceImpl(new CarrierSQLRepoImpl()),
                    new CargoServiceImpl(new CargoSQLRepoImpl()),
                    new TransportationServiceImpl(new TransportationSQLRepoImpl())
            );
      default: {
          throw new RuntimeException("Service Holder died");
      }
    }
  }

  public CarrierService getCarrierService() {
    return carrierService;
  }

  public CargoService getCargoService() {
    return cargoService;
  }

  public TransportationService getTransportationService() {
    return transportationService;
  }
}
