package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2.a_3_converter;

@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from);
}
