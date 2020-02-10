package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_1.a_1_collection.jdk12;

public interface SuperCollection<T> {
    void add(T t);

    int size();

    default void delete() {
        System.out.println("sdsd");
    }

}
