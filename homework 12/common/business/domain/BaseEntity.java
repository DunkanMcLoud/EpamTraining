package ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.domain;

public abstract class BaseEntity {
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
