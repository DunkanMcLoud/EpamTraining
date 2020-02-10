package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_1.a_4_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class A_3_LambdaDemo {



    public static void main(String[] args) throws Exception {
    }

    private static void demoLambdaPointerToStaticMethod() {
        new Thread(() -> A_3_LambdaDemo.hello());
        new Thread(A_3_LambdaDemo::hello);
    }

    static void hello() {
        System.out.println("Hello");
    }

    private static void demo1() throws Exception {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Hello ");
                    }
                }
        );
        thread.start();
        thread.join();

        thread = new Thread(() -> {
            System.out.println("Hello ");
        });

        thread = new Thread(() -> System.out.println("Hello "));

        List<String> list = new ArrayList<>();
        list.add("SS");
        list.add("AA");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.sort((String o1, String o2) -> {
            return o1.compareTo(o2);
        });

        list.sort((o1, o2) -> {
            return o1.compareTo(o2);
        });

        list.sort((o1, o2) -> o1.compareTo(o2));
    }

}
