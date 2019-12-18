package transportation.repo.impl;

import storage.IdGenerator;
import transportation.domain.Transportation;
import transportation.repo.TransportationRepo;

import java.util.ArrayList;
import java.util.List;

import static storage.Storage.*;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    private static List<Transportation> EMPTY_TRANSPORTATION_LIST = new ArrayList<>();

    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationsList.add(transportation);
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportationsList) {
            if (Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        System.out.println("Transportation not exists");
        return null;
    }

    @Override
    public void deleteById(long id) {
        List<Transportation> answer = EMPTY_TRANSPORTATION_LIST;
        boolean isTransportationFound = false;
        for (Transportation transportation : transportationsList) {
            if (Long.valueOf(id).equals(transportation.getId())) {
                isTransportationFound = true;
                answer.add(transportation);
            }
        }
        if (isTransportationFound) {
            transportationsList.removeAll(answer);
        }
    }

}
