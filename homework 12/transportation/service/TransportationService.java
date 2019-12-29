package ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.service;

import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.service.CommonService;
import ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.domain.Transportation;

public interface TransportationService extends CommonService<Transportation, Long> {
}
