package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.repo;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
