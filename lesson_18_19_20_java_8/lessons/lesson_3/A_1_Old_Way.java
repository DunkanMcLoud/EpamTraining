package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class A_1_Old_Way {

    public static void main(String[] args) throws Exception {
        // a_2_demoConvertToString();
        //  a_3_demoConvertFromString();

        // System.out.println(new Date().getTime());
        //  System.out.println(a_3_demoConvertFromString("11.11.1969").getTime());
        demoCalendar(new Date());
    }

    private static void a_1_demoDateCreationAndSetFields() {
        Date date = new Date();
        date.setYear(2005);
        date.setMinutes(55);
    }

    private static void a_2_demoConvertToString() {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = simpleDateFormat.format(new Date());
        System.out.println(formattedDate);
    }

    private static String a_2_demoConvertToString(Date date) {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(date);
    }

    private static void a_3_demoConvertFromString() throws ParseException {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String dateStr = "11.07.2005 16:43";

        Date parsed = simpleDateFormat.parse(dateStr);
        System.out.println(parsed);
    }

    private static Date a_3_demoConvertFromString(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd.MM.yyyy");

        return simpleDateFormat.parse(dateStr);
    }


    private static void demoCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);

        Date dateNew = new Date(calendar.getTimeInMillis());
        System.out.println(a_2_demoConvertToString(dateNew));
    }

}
