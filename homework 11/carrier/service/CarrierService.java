package ru.epam.javacore.lesson_10_generics.homework.carrier.service;

import ru.epam.javacore.lesson_10_generics.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_10_generics.homework.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

  Carrier getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

}
