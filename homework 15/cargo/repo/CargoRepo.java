package ru.epam.javacore.lesson_14_serialization.homework.cargo.repo;

import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_14_serialization.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
