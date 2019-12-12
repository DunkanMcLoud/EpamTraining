package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

class TransportationStorage {
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

    Transportation searchByName(String name) {
        for (Transportation transportation : transportations) {
            if (transportation != null && transportation.getBillTo().toLowerCase().equals(name)) {
                return transportation;
            }
        }
        return null;
    }
}


