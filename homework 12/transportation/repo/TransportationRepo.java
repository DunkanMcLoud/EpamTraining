package ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.repo;

import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.repo.CommonRepo;
import ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo<Transportation, Long> {
}
