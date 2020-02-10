package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.repo;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
