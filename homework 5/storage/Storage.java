package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.cargo.Cargo;
import ru.epam.javacore.lesson_4.homework.carrier.Carrier;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

public class Storage {
    protected static final int MIN_ARRAY_SIZE = 1; //i've putted 1 in order to test resizing of array
    static CargoStorage cargos = new CargoStorage(MIN_ARRAY_SIZE);
    static CarrierStorage carriers = new CarrierStorage(MIN_ARRAY_SIZE);
    static TransportationStorage transportations = new TransportationStorage(MIN_ARRAY_SIZE);

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


    public static String getByName(String name) {
        String low_name = name.toLowerCase();
        Object[] searchInCargos = cargos.searchByName(low_name);
        Object[] searchInCarriers = carriers.searchByName(low_name);
        Object[] searchInTransportation = transportations.searchByName(low_name);
        Object[] answer = mergeArrays(searchInCargos, searchInCarriers, searchInTransportation);
        if (answer != null) {
            return displayAsString(answer);
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    private static String displayAsString(Object[] array) {
        StringBuilder answer = new StringBuilder();
        for (Object obj : array) {
            if (obj != null) {
                answer.append(obj.toString());
                answer.append(" ; \n");
            }
        }
        return answer.toString();
    }

    private static Object[] mergeArrays(Object[] inCargos, Object[] inCarriers, Object[] inTransportation) {
        Object[] answer = new Object[inCargos.length + inCarriers.length + inTransportation.length];
        int index = 0;
        for (Object obj : inCargos) {
            if (obj != null) {
                answer[index] = obj;
                index++;
            }
        }
        for (Object obj : inCarriers) {
            if (obj != null) {
                answer[index] = obj;
                index++;
            }
        }
        for (Object obj : inTransportation) {
            if (obj != null) {
                answer[index] = obj;
                index++;
            }
        }
        return answer;
    }

    private static class CargoStorage {
        private int lastIndex = 0;
        private Cargo[] cargos;
        private int cargosSize;

        CargoStorage(int sizeOfstorage) {
            this.cargos = new Cargo[sizeOfstorage];
            this.cargosSize = cargos.length;
        }

        void addCargo(Cargo cargoToAdd) {
            if (lastIndex == (cargosSize - 1)) {
                resize(cargosSize * 2);
            }
            cargos[lastIndex++] = cargoToAdd;

        }

        private void resize(int capacity) {
            Cargo[] copy = new Cargo[capacity];
            System.arraycopy(cargos, 0, copy, 0, lastIndex + 1);
            cargos = copy;
            cargosSize = cargos.length;
        }

        void displayCargos() {
            for (Cargo i : cargos) {
                if (i != null) {
                    System.out.println(i);
                }
            }
        }

        Cargo searchID(long id) {
            for (Cargo cargo : cargos) {
                if (cargo != null && id == cargo.getId()) {
                    return cargo;
                }
            }
            return null;
        }

        Cargo[] searchByName(String name) {
            Cargo[] answer = new Cargo[1];
            int counter = 0;
            for (Cargo cargo : cargos) {
                if (cargo != null && cargo.getName().toLowerCase().equals(name)) {
                    if (counter == (answer.length - 1)) {
                        Cargo[] copy = new Cargo[answer.length * 2];
                        System.arraycopy(answer, 0, copy, 0, answer.length - 1);
                        answer = copy;
                    }
                    answer[counter++] = cargo;
                }
            }
            return answer;
        }
    }

    private static class CarrierStorage {
        private int lastIndex = 0;
        private Carrier[] carriers;
        private int carriersSize;

        CarrierStorage(int sizeOfstorage) {
            this.carriers = new Carrier[sizeOfstorage];
            this.carriersSize = carriers.length;
        }

        void addCarrier(Carrier carrierToAdd) {
            if (lastIndex == (carriersSize - 1)) {
                resize(carriersSize * 2);
            }
            carriers[lastIndex++] = carrierToAdd;

        }

        void displayCarriers() {
            for (Carrier i : carriers) {
                if (i != null) {
                    System.out.println(i);
                }
            }
        }


        private void resize(int capacity) {
            Carrier[] copy = new Carrier[capacity];
            System.arraycopy(carriers, 0, copy, 0, lastIndex + 1);
            carriers = copy;
            carriersSize = carriers.length;
        }

        Carrier searchID(long id) {
            for (Carrier carrier : carriers) {
                if (carrier != null && id == carrier.getId()) {
                    return carrier;
                }
            }
            return null;
        }


        Carrier[] searchByName(String name) {
            Carrier[] answer = new Carrier[1];
            int counter = 0;
            for (Carrier carrier : carriers) {
                if (carrier != null && carrier.getName().toLowerCase().equals(name)) {
                    if (counter == (answer.length - 1)) {
                        Carrier[] copy = new Carrier[answer.length * 2];
                        System.arraycopy(answer, 0, copy, 0, answer.length - 1);
                        answer = copy;
                    }
                    answer[counter++] = carrier;
                }
            }
            return answer;
        }
    }

    private static class TransportationStorage {
        private int lastIndex = 0;
        private Transportation[] transportations;
        private int transportationSize;

        TransportationStorage(int sizeOfstorage) {
            this.transportations = new Transportation[sizeOfstorage];
            this.transportationSize = transportations.length;
        }

        void addTransportation(Transportation transportationToAdd) {
            if (lastIndex == (transportationSize - 1)) {
                resize(transportationSize * 2);
            }
            transportations[lastIndex++] = transportationToAdd;

        }

        void displayTransportations() {
            for (Transportation i : transportations) {
                if (i != null) {
                    System.out.println(i);
                }
            }
        }


        private void resize(int capacity) {
            Transportation[] copy = new Transportation[capacity];
            System.arraycopy(transportations, 0, copy, 0, lastIndex + 1);
            transportations = copy;
            transportationSize = transportations.length;
        }

        Transportation searchID(long id) {
            for (Transportation transportation : transportations) {
                if (transportation != null && id == transportation.getId()) {
                    return transportation;
                }
            }
            return null;
        }

        Transportation[] searchByName(String name) {
            Transportation[] answer = new Transportation[1];
            int counter = 0;
            for (Transportation transportation : transportations) {
                if (transportation != null && transportation.getBillTo().toLowerCase().equals(name)) {
                    if ((answer.length - 1) == counter) {
                        Transportation[] copy = new Transportation[answer.length * 2];
                        System.arraycopy(answer, 0, copy, 0, answer.length - 1);
                        answer = copy;
                    }
                    answer[counter++] = transportation;
                }
            }
            return answer;
        }
    }

    private Storage() {
    }



}



