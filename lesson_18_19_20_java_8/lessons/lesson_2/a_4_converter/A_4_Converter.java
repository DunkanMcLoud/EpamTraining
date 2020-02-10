package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2.a_4_converter;

import java.util.Optional;

public class A_4_Converter {


    public Optional<Integer> parseString(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        Optional<Integer> integer = Optional.of(1);
        Optional<Integer> integerEmpty = Optional.empty();
        Optional<Integer> integerEmptyOrNot = Optional.ofNullable(1);
        Optional<Integer> integerEmptyOrNot2 = Optional.ofNullable(null);


        MyOptional<Integer> of = MyOptional.of(1);

        A_4_Converter a_4_converter = new A_4_Converter();
        Optional<Integer> boxWithNumber = a_4_converter.parseString("3klgiuio");
        if (boxWithNumber.isPresent()) {
            Integer i = boxWithNumber.get();
            i++;
            System.out.println(i);
        }
    }

    private static Optional<String> game(String user) {
        if ("Dima Yusupov".equals(user)) {
            return Optional.of(user);
        } else {
            String result = null;

            if (user.length() > 5) {
                result = user;
            }

            return Optional.ofNullable(result);

        }
    }


    private static class MyOptional<T> {
        T value;

        private MyOptional(T t) {
            this.value = t;
        }

        public static <T> MyOptional<T> of(T t) {
            return new MyOptional<>(t);
        }

        public static <T> MyOptional<T> empty() {
            return new MyOptional<>(null);
        }
    }
}
