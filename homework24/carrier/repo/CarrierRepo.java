package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Optional<Carrier> getByIdFetchingTransportations(long id);

  List<Carrier> findByName(String name);

}
