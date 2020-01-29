package ru.epam.javacore.lesson_16_concurrency.homework.carrier.repo;

import ru.epam.javacore.lesson_16_concurrency.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.exception.checked.CarrierNotExistsException;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id) throws CarrierNotExistsException;

  Carrier[] findByName(String name);

}
