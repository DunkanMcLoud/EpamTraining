package ru.epam.javacore.lesson_12_io_nio.homework.carrier.service;

import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_12_io_nio.homework.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

  Carrier getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

}
