package ru.epam.javacore.lesson_12_io_nio.homework.cargo.service;

import ru.epam.javacore.lesson_12_io_nio.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_12_io_nio.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_12_io_nio.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
