package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.cargo.Cargo;
import ru.epam.javacore.lesson_4.homework.carrier.Carrier;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

public class Storage {
    protected static final int MIN_ARRAY_SIZE = 1; //i've putted 1 in order to test resizing of array
    static CargoStorage cargos = new CargoStorage(MIN_ARRAY_SIZE);
    static CarrierStorage carriers = new CarrierStorage(MIN_ARRAY_SIZE);
    static TransportationStorage transportations = new TransportationStorage(MIN_ARRAY_SIZE);

    private Storage() {
    }

    public static void addCargo(Cargo cargo) {
        cargos.addCargo(cargo);
    }

    public static void addCarrier(Carrier carrier) {
        carriers.addCarrier(carrier);
    }


    public static void addTransportation(Transportation transportation) {
        transportations.addTransportation(transportation);
    }

    public static void displayAllCargo() {
        cargos.displayCargos();
    }

    public static void displayAllCarriers() {
        carriers.displayCarriers();
    }

    public static void displayAllTransportation() {
        transportations.displayTransportations();
    }

    //TODO finish 6 functions below
    public static Object getbyID(long id) {
        Cargo searchInCargos = cargos.searchID(id);
        if (searchInCargos != null) {
            return searchInCargos;
        } else {
            Carrier searchInCarriers = carriers.searchID(id);
            if (searchInCarriers != null) {
                return searchInCarriers;
            } else {
                Transportation searchInTransportation = transportations.searchID(id);
                if (searchInTransportation != null) {
                    return searchInTransportation;
                } else {
                    System.out.println("Object not exists");
                    return null;
                }
            }
        }
    }


    public static Object getByName(String name){
        String low_name = name.toLowerCase();
        Cargo searchInCargos = cargos.searchByName(low_name);
        if (searchInCargos != null) {
            return searchInCargos;
        } else {
            Carrier searchInCarriers = carriers.searchByName(low_name);
            if (searchInCarriers != null) {
                return searchInCarriers;
            } else {
                Transportation searchInTransportation = transportations.searchByName(low_name);
                if (searchInTransportation != null) {
                    return searchInTransportation;
                } else {
                    System.out.println("Object not exists");
                    return null;
                }
            }
        }
    }


}
