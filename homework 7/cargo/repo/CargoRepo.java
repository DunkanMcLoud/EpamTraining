package cargo.repo;

import cargo.domain.Cargo;
import common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] getByName(String name);

  void displayAllCargosInCollection();
}
