package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CommonService<TYPE, ID> {
  Optional<TYPE> findById(ID id);

  void save(TYPE entity);

  boolean update(TYPE entity);

  boolean deleteById(ID id);

  List<TYPE> getAll() throws SQLException;

  int countAll() throws SQLException;

  void printAll() throws SQLException;
}
