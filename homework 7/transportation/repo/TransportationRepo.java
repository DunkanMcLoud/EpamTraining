package transportation.repo;


import common.business.repo.CommonRepo;
import transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(long id);
}
