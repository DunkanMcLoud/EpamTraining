package ru.epam.javacore.lesson_14_serialization.homework.cargo.service;

import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_14_serialization.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
