package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.service;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CarrierService extends CommonService<Carrier, Long> {

  Optional<Carrier> getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

}
