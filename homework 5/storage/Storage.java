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

    public static Cargo getCargoByID(long id) {
        Cargo answer = cargos.searchByIDs(id);
        if (answer != null) {
            return answer;
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    public static Cargo[] getCargosByName(String name) {
        Cargo[] answer = cargos.searchByName(name);
        return answer;
    }

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

    public static Carrier[] getCarrierByName(String name) {
        Carrier[] answer = carriers.searchByName(name);
        return answer;
    }

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

    public static Transportation[] getTransportationsByName(String name) {
        Transportation[] answer = transportations.searchByName(name);
        return answer;
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


    private Storage() {
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
    }


}



