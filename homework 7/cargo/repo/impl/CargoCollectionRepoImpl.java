package cargo.repo.impl;

import cargo.domain.Cargo;
import cargo.repo.CargoRepo;
import storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;

import static storage.Storage.cargosList;

public class CargoCollectionRepoImpl implements CargoRepo {
    private static List<Cargo> EMPTY_CARGO_LIST = new ArrayList<>();
    private static Cargo[] EMPTY_CARGO_ARRAY = new Cargo[10];


    @Override
    public void displayAllCargosInCollection() {
        for (Cargo cargo : cargosList) {
            System.out.println(cargo.toString());
        }
    }

    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargosList.add(cargo);
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo cargo : cargosList) {
            if (Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        System.out.println("Cargo not exists");
        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        if (cargosList.isEmpty()) {
            return null;
        } else {
            Cargo[] answer = new Cargo[cargosList.size()];
            int index = 0;
            for (Cargo cargo : cargosList) {
                if (cargo.getName().toLowerCase().strip().equals(name.toLowerCase().strip())) {
                    answer[index++] = cargo;
                }
            }
            answer = removeNullsFromArray(answer);
            return answer;
        }
    }

    @Override
    public void deleteById(long id) {
        List<Cargo> answer = EMPTY_CARGO_LIST;
        boolean isCargoFound = false;
        for (Cargo cargo : cargosList) {
            if (Long.valueOf(id).equals(cargo.getId())) {
                isCargoFound = true;
                answer.add(cargo);
            }
        }
        if (isCargoFound) {
            cargosList.removeAll(answer);
        }
    }

    private Cargo[] removeNullsFromArray(Cargo[] arr) {
        int counter = 0;
        for (Cargo cargo : arr) {
            if (cargo != null) {
                counter++;
            } else {
                break;
            }
        }
        Cargo[] answer = new Cargo[counter];
        System.arraycopy(arr, 0, answer, 0, counter);
        return answer;
    }


}
