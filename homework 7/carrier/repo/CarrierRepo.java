package carrier.repo;

import carrier.domain.Carrier;
import common.business.repo.CommonRepo;
public interface CarrierRepo extends CommonRepo {

  void add(Carrier carrier);

  Carrier getById(long id);

  Carrier[] getByName(String name);

}
