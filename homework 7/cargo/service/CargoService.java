package cargo.service;

import cargo.domain.Cargo;
import common.business.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);
}
