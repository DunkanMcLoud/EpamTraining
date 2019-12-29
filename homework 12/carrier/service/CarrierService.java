package ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.service;

import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

  Carrier getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

}
