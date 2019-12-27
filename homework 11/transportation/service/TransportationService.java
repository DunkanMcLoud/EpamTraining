package ru.epam.javacore.lesson_10_generics.homework.transportation.service;

import ru.epam.javacore.lesson_10_generics.homework.common.business.service.CommonService;
import ru.epam.javacore.lesson_10_generics.homework.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService extends CommonService<Transportation, Long> {
}
