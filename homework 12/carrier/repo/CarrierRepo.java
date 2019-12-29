package ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.repo;

import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
