package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_1.a_3_default_methods_problem;

public interface A {
    default void method() {
        System.out.println("This is method A");
    }
}
