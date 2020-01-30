package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils;

import java.text.SimpleDateFormat;

public class DateHandler {
    private static String PATTERN_WITHOUT_TIME = "dd.MM.yyyy";
    private static String PATTERN_WITH_TIME = "dd.MM.yyyy HH:mm";
    private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat(PATTERN_WITHOUT_TIME);
    private static SimpleDateFormat dateFormatWithTime = new SimpleDateFormat(PATTERN_WITH_TIME);

    public static SimpleDateFormat getDateParserWithoutTime(){
        return simpleDateFormat;
    }

    public static SimpleDateFormat getDateParserWithTime(){
        return dateFormatWithTime;
    }
}
