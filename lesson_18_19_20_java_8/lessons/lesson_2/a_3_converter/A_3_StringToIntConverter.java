package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2.a_3_converter;

public class A_3_StringToIntConverter
        implements Converter<String, Integer> {

    @Override
    public Integer convert(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {
        A_3_StringToIntConverter converter =new A_3_StringToIntConverter();
        Integer convert = converter.convert("lnlnlnk");
        convert++;
        System.out.println(convert);
    }
}
