package ru.epam.javacore.lesson_4.homework.storage;

public final class ID_generator {
    private static long id = 0L;

    public static long generateID(){
        return id++;
    }
}
