package transportation.repo.impl;

import common.solutions.utils.ArrayUtils;
import storage.IdGenerator;
import storage.Storage;
import transportation.domain.Transportation;
import transportation.repo.TransportationRepo;

import static common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static storage.Storage.transportationIndex;
import static storage.Storage.transportations;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == Storage.transportations.length) {
            Transportation[] newTransportations =
                    new Transportation[Storage.transportations.length * 2];
            ArrayUtils.copyArray(Storage.transportations, newTransportations);
            Storage.transportations = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        Storage.transportations[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : Storage.transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public void deleteById(long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(transportations, id);

        if (indexToDelete == null) {
        } else {
            ArrayUtils.removeElement(transportations, indexToDelete);

        }
    }
}
