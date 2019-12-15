package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_4.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_4.homework.transportation.domain.Transportation;

import java.util.Iterator;

public class Storage {
    protected static final int MIN_ARRAY_SIZE = 1; //i've putted 1 in order to test resizing of array
    static CargoStorage cargos = new CargoStorage(MIN_ARRAY_SIZE);
    static CarrierStorage carriers = new CarrierStorage(MIN_ARRAY_SIZE);
    static TransportationStorage transportations = new TransportationStorage(MIN_ARRAY_SIZE);


    //For Cargos
    public static void addCargo(Cargo cargo) {
        cargos.addCargo(cargo);
    }

    public static Cargo getCargoByID(long id) {
        Cargo answer = cargos.searchByIDs(id);
        if (answer != null) {
            return answer;
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    public static void deleteCargoByID(long id) {
        cargos.deleteByID(id);
    }

    public static Object[] getCargosByName(String name) {
        Cargo[] answer = cargos.searchByName(name);
        return removeNulls(answer);
    }

    public static void displayAllCargo() {
        cargos.displayCargos();
    }


    //For Carriers
    public static void addCarrier(Carrier carrier) {
        carriers.addCarrier(carrier);
    }

    public static Carrier getCarrierByID(long id) {
        Carrier answer = carriers.searchByIDs(id);
        if (answer != null) {
            return answer;
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    public static void deleteCarrierByID(long id) {
        carriers.deleteByID(id);
    }


    public static Object[] getCarrierByName(String name) {
        Carrier[] answer = carriers.searchByName(name);
        return removeNulls(answer);
    }

    public static void displayAllCarriers() {
        carriers.displayCarriers();
    }


    //For Transportations

    public static void addTransportation(Transportation transportation) {
        transportations.addTransportation(transportation);
    }

    public static Transportation getTransportationByID(long id) {
        Transportation answer = transportations.searchByIDs(id);
        if (answer != null) {
            return answer;
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    public static Object[] getTransportationsByName(String name) {
        Transportation[] answer = transportations.searchByName(name);
        return removeNulls(answer);
    }

    public static void displayAllTransportation() {
        transportations.displayTransportations();
    }

    public static void deleteTransportationByID(long id){
        transportations.deleteByID(id);
    }


    //Helpers
    private static Object[] removeNulls(Object[] array) {
        int counter = 0;
        for (Object obj : array) {
            if (obj != null) {
                counter++;
            }
        }
        Object[] new_array = new Object[counter];
        System.arraycopy(array, 0, new_array, 0, counter);
        return new_array;
    }


    //Private Classes

    private Storage() {
    }


    private static class CargoStorage {
        private int lastIndex = 0;
        protected Cargo[] cargos;
        private int cargosSize;
        private int quantity = 0;

        CargoStorage(int sizeOfstorage) {
            this.cargos = new Cargo[sizeOfstorage];
            this.cargosSize = cargos.length;
        }

        void addCargo(Cargo cargoToAdd) {
            if (lastIndex == (cargosSize - 1)) {
                resize(cargosSize * 2);
            }
            cargos[lastIndex++] = cargoToAdd;
            quantity++;

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

        Cargo searchByIDs(long id) {
            for (Cargo cargo : cargos) {
                if (cargo != null && id == cargo.getId()) {
                    return cargo;
                }
            }
            return null;
        }

        Cargo[] searchByName(String name) {
            name = name.toLowerCase();
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

        private Iterator<Cargo> iterator() {
            Iterator<Cargo> iterator = new Iterator<Cargo>() {
                int length = cargosSize;
                int ind = -1;


                @Override
                public boolean hasNext() {
                    return (cargos[ind + 1] != null);
                }

                @Override
                public Cargo next() {
                    return cargos[++ind];
                }

                @Override
                public void remove() {
                    Cargo[] new_array = new Cargo[length - 1];
                    int start = ind + 1;
                    for (int i = 0; i < ind; i++) {
                        new_array[i] = cargos[i];
                    }
                    for (int i = ind, g = start; g < length; i++, g++) {
                        new_array[i] = cargos[g];
                    }
                    cargos = new_array;
                    lastIndex--;
                    cargosSize--;
                }
            };
            return iterator;
        }

        private void deleteByID(long id) {
            Iterator<Cargo> itr = iterator();
            while (itr.hasNext()) {
                Cargo item = itr.next();
                if (item.getId().equals(Long.valueOf(id))) {
                    itr.remove();
                    break;
                }
            }
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

        Carrier searchByIDs(long id) {
            for (Carrier carrier : carriers) {
                if (carrier != null && id == carrier.getId()) {
                    return carrier;
                }
            }
            return null;
        }


        Carrier[] searchByName(String name) {
            name = name.toLowerCase();
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

        private Iterator<Carrier> iterator() {
            Iterator<Carrier> iterator = new Iterator<Carrier>() {
                int length = carriersSize;
                int ind = -1;


                @Override
                public boolean hasNext() {
                    return (carriers[ind + 1] != null);
                }

                @Override
                public Carrier next() {
                    return carriers[++ind];
                }

                @Override
                public void remove() {
                    Carrier[] new_array = new Carrier[length - 1];
                    int start = ind + 1;
                    for (int i = 0; i < ind; i++) {
                        new_array[i] = carriers[i];
                    }
                    for (int i = ind, g = start; g < length; i++, g++) {
                        new_array[i] = carriers[g];
                    }
                    carriers = new_array;
                    lastIndex--;
                    carriersSize--;
                }
            };
            return iterator;
        }

        private void deleteByID(long id) {
            Iterator<Carrier> itr = iterator();
            while (itr.hasNext()) {
                Carrier item = itr.next();
                if (item.getId().equals(Long.valueOf(id))) {
                    itr.remove();
                    break;
                }
            }
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

        Transportation searchByIDs(long id) {
            for (Transportation transportation : transportations) {
                if (transportation != null && id == transportation.getId()) {
                    return transportation;
                }
            }
            return null;
        }

        Transportation[] searchByName(String name) {
            name = name.toLowerCase();
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

        private Iterator<Transportation> iterator() {
            Iterator<Transportation> iterator = new Iterator<Transportation>() {
                int length = transportationSize;
                int ind = -1;


                @Override
                public boolean hasNext() {
                    return (transportations[ind + 1] != null);
                }

                @Override
                public Transportation next() {
                    return transportations[++ind];
                }

                @Override
                public void remove() {
                    Transportation[] new_array = new Transportation[length - 1];
                    int start = ind + 1;
                    for (int i = 0; i < ind; i++) {
                        new_array[i] = transportations[i];
                    }
                    for (int i = ind, g = start; g < length; i++, g++) {
                        new_array[i] = transportations[g];
                    }
                    transportations = new_array;
                    lastIndex--;
                    transportationSize--;
                }
            };
            return iterator;
        }

        private void deleteByID(long id) {
            Iterator<Transportation> itr = iterator();
            while (itr.hasNext()) {
                Transportation item = itr.next();
                if (item.getId().equals(Long.valueOf(id))) {
                    itr.remove();
                    break;
                } else{
                    System.out.println("No such item!");
                }
            }
        }
    }


}



