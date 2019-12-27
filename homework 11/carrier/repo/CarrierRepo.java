package ru.epam.javacore.lesson_10_generics.homework.carrier.repo;

import ru.epam.javacore.lesson_10_generics.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_10_generics.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
