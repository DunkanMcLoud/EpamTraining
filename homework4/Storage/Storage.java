package ru.epam.javacore.lesson_4.homework.Storage;

import ru.epam.javacore.lesson_4.homework.cargo.Cargo;
import ru.epam.javacore.lesson_4.homework.carrier.Carrier;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

public class Storage {
    protected static final int MIN_ARRAY_SIZE = 1; //i've putted 1 in order to test resizing of array
    static CargoStorage cargos = new CargoStorage(MIN_ARRAY_SIZE);
    static CarrierStorage carriers = new CarrierStorage(MIN_ARRAY_SIZE);
    static TransportationStorage transportations = new TransportationStorage(MIN_ARRAY_SIZE);

    private Storage(){}

    public static void addCargo(Cargo cargo) {
        cargos.addCargo(cargo);
    }

    public static void addCarrier(Carrier carrier){
        carriers.addCarrier(carrier);
    }

    public static void addTransportation(Transportation transportation) {
        transportations.addTransportation(transportation);
    }

    public static void displayAllCargo(){
        cargos.displayCargos();
    }

    public static void displayAllCarriers(){
        carriers.displayCarriers();
    }

    public static void displayAllTransportation(){
        transportations.displayTransportations();
    }


}
