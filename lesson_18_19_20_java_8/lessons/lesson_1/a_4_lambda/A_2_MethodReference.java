package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_1.a_4_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class A_2_MethodReference {
    private static class Person {

        private int age;

        public void runThread() {
            new Thread(() -> this.sayHello());
            new Thread(this::sayHello);
            new Thread();
        }

        public void ss() {
            new Thread(() -> new Person());
            new Thread(Person::new);

        }

        public void runThread2() {
            Person person = new Person();
            new Thread(() -> person.sayHello());
            new Thread(person::sayHello);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Person.this.getAge();
                }
            });

            new Thread(() -> {
                int a = this.getAge();
                System.out.println(a);
            });
            new Thread(this::getAge);
        }

        public void sayHello() {
        }


//        public void sayHello(func (String a, String B): C) {
//         var c = func(a,b);
//        }

        public int getAge() {
            return this.age;
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        list.sort((String a, String b) -> A_2_MethodReference.compare(a, b));
        list.sort((a, b) -> A_2_MethodReference.compare(a, b));
        list.sort(A_2_MethodReference::compare);
        //   list.sort(A_2_MethodReference::compare22);
    }

    private static int compare(String s1, String s2) {
        return 0;
    }

    private static void compare22(String s1, String s2) {

    }
}
