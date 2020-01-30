package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.domain;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
