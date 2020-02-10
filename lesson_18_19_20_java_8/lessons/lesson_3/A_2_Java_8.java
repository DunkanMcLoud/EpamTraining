package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_3;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class A_2_Java_8 {

    public static void main(String[] args) {
       // Instant instant = Instant.now();
     //   System.out.println(instant);

       // demoZonedDateTime_1();
       // demoZonedDateTime_2();
    }

    private static void demoZonedDateTime_1(){
        LocalDateTime localDateTime =
                LocalDateTime.of(2005,11,20,1,1);
        ZonedDateTime zonedDateTime = ZonedDateTime.from(localDateTime);
        System.out.println(zonedDateTime);
    }

    private static void demoZonedDateTime_2(){
        LocalDateTime localDateTime =
                LocalDateTime.of(2005,11,20,1,1);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,
                                                       ZoneId.of("Europe/Moscow"));
        System.out.println(zonedDateTime);
    }
}
