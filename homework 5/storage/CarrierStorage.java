package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.carrier.Carrier;

class CarrierStorage {
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

    Carrier searchByName(String name) {
        for (Carrier carrier : carriers) {
            if (carrier != null && carrier.getName().toLowerCase().equals(name)) {
                return carrier;
            }
        }
        return null;
    }
}

