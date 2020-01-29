package ru.epam.javacore.lesson_16_concurrency.homework.storage.initor;


import ru.epam.javacore.lesson_16_concurrency.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.exception.checked.CargoNotExistsEsception;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.exception.checked.CarrierNotExistsException;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_16_concurrency.homework.transportation.service.TransportationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static ru.epam.javacore.lesson_16_concurrency.homework.common.solutions.utils.CollectionUtils.isNotEmpty;

public class InMemoryStorageInitor implements StorageInitor {

    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public InMemoryStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() throws CargoNotExistsEsception, CarrierNotExistsException {
        initCargos();
        initCarriers();
        initTransportations();
        appendTransportationsToCargos();
    }

    private void initCargos() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.save(createClothersCargo(i));
        }
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.save(createFoodCargo(i));
        }
    }

    private ClothersCargo createClothersCargo(int index) {
        ClothersCargo cargo = new ClothersCargo();
        cargo.setSize("Clothers_Size_" + index);
        cargo.setName("Jeans");
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));

        return cargo;
    }

    private FoodCargo createFoodCargo(int index) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(new Date());
        cargo.setStoreTemperature(index);
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));
        cargo.setName("Milk");

        return cargo;
    }

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.save(carrier);
        }
    }

    private Carrier createCarrier(int index) {
        Carrier carrier = new Carrier();
        carrier.setName("Carrier_Name");
        carrier.setAddress("Address_" + index);
        return carrier;
    }

    private void initTransportations() throws CarrierNotExistsException, CargoNotExistsEsception {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
            transportationService.save(transportation);
        }
    }

    private Transportation createTransportation(long cargoId, long carrierId) throws CargoNotExistsEsception, CarrierNotExistsException {
        Transportation transportation = new Transportation();
        Optional<Cargo> cargoToSet = cargoService.findById(cargoId);
        if (cargoToSet.isPresent()) transportation.setCargo(cargoToSet.get());
        else {
            throw new CargoNotExistsEsception();
        }
        Optional<Carrier> carrierToSet = carrierService.findById(carrierId);
        if (carrierToSet.isPresent()) transportation.setCarrier(carrierToSet.get());
        else {
            throw new CarrierNotExistsException();
        }
        transportation.setDescription("Transportation");

        return transportation;
    }

    private void appendTransportationsToCargos() {
        List<Cargo> cargos = cargoService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (isNotEmpty(cargos) && isNotEmpty(transportations)) {
            for (Cargo cargo : cargos) {
                appendTransportationsToCargo(cargo, transportations);
            }
        }
    }

    private void appendTransportationsToCargo(Cargo cargo,
                                              List<Transportation> transportations) {

        List<Transportation> cargoTransportations = cargo.getTransportations();
        if (cargoTransportations == null) {
            cargoTransportations = new ArrayList<>();
        }

        for (Transportation transportation : transportations) {
            if (transportation.getCargo() != null && transportation.getCargo().getId()
                    .equals(cargo.getId())) {
                cargoTransportations.add(transportation);
            }
        }

        cargo.setTransportations(transportations);
    }
}
