package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.mappers;

public interface JdbcFunction <FROM,TO> {
    TO apply (FROM from) throws Exception;
}
