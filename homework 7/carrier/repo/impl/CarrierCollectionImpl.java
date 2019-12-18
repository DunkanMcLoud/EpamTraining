package carrier.repo.impl;

import cargo.domain.Cargo;
import carrier.domain.Carrier;
import carrier.repo.CarrierRepo;
import common.solutions.utils.ArrayUtils;
import storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;

import static storage.Storage.carriersList;

public class CarrierCollectionImpl implements CarrierRepo {

    private static List<Carrier> EMPTY_CARRIER_LIST = new ArrayList<>();

    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carriersList.add(carrier);
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carriersList) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        System.out.println("Carrier not exists");
        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        if (carriersList.isEmpty()) {
            return null;
        } else {
            Carrier[] answer = new Carrier[carriersList.size()];
            int index = 0;
            for (Carrier carrier : carriersList) {
                if (carrier.getName().toLowerCase().strip().equals(name.toLowerCase().strip())) {
                    answer[index++] = carrier;
                }
            }
            answer = removeNullsFromArray(answer);
            return answer;
        }
    }

    private Carrier[] removeNullsFromArray(Carrier[] arr) {
        int counter = 0;
        for (Carrier carrier : arr) {
            if (carrier != null) {
                counter++;
            } else {
                break;
            }
        }
        Carrier[] answer = new Carrier[counter];
        System.arraycopy(arr, 0, answer, 0, counter);
        return answer;
    }

    @Override
    public void deleteById(long id) {
        List<Carrier> answer = EMPTY_CARRIER_LIST;
        boolean isCarrierFound = false;
        for (Carrier carrier : carriersList) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                isCarrierFound = true;
                answer.add(carrier);
            }
        }
        if (isCarrierFound) {
            carriersList.removeAll(answer);
        }
    }


}
