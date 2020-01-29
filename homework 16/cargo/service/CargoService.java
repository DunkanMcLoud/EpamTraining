package ru.epam.javacore.lesson_16_concurrency.homework.cargo.service;

import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id) throws IllegalArgumentException;

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
