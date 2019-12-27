package ru.epam.javacore.lesson_10_generics.homework.transportation.repo;

import ru.epam.javacore.lesson_10_generics.homework.common.business.repo.CommonRepo;
import ru.epam.javacore.lesson_10_generics.homework.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo<Transportation, Long> {
}
