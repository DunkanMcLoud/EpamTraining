package ru.epam.javacore.lesson_4.homework.common.domain;

import ru.epam.javacore.lesson_4.homework.storage.ID_generator;

public abstract class BaseEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = ID_generator.generateID();
    }
}
