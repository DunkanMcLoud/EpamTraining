package ru.epam.javacore.lesson_4.homework.storage;

import ru.epam.javacore.lesson_4.homework.cargo.Cargo;

class CargoStorage {
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

    Cargo searchByName(String name) {
        for (Cargo cargo : cargos) {
            if (cargo != null && cargo.getName().toLowerCase().equals(name)) {
                return cargo;
            }
        }
        return null;

    }
}

