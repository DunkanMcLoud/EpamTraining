package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.repo;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.domain.BaseEntity;

import java.util.Optional;

public final class CommonRepoHelper {

  private CommonRepoHelper() {

  }

  public static Optional<Integer> findEntityIndexInArrayStorageById(BaseEntity[] data, long entityId) {
    for (int i = 0; i < data.length; i++) {
      if (Long.valueOf(entityId).equals(data[i].getId())) {
        return Optional.of(i);
      }
    }

    return Optional.empty();
  }
}
