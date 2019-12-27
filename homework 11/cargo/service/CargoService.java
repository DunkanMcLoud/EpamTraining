package ru.epam.javacore.lesson_10_generics.homework.cargo.service;

import ru.epam.javacore.lesson_10_generics.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_10_generics.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_10_generics.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
