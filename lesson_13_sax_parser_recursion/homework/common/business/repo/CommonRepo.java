package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.repo;

import java.util.List;

public interface CommonRepo<TYPE, ID> {

  TYPE findById(ID id);

  void save(TYPE entity);

  boolean update(TYPE entity);

  boolean deleteById(ID id);

  List<TYPE> getAll();

  int countAll();
}
