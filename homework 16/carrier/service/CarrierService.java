package ru.epam.javacore.lesson_16_concurrency.homework.carrier.service;

import ru.epam.javacore.lesson_16_concurrency.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_16_concurrency.homework.carrier.exception.checked.CarrierNotExistsException;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

  Carrier getByIdFetchingTransportations(Long id) throws CarrierNotExistsException;

  List<Carrier> findByName(String name);

}
