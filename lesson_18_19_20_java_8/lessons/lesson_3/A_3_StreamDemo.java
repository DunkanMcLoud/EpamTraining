package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_3;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A_3_StreamDemo {

    public static void main(String[] args) {
        // a_2_demoStreamFromCollection();
        //  a_3_demoStreamFromCollectionError();
        a_4_demoCollectionStream();
    }

    private static void a_1_demoStreamCreation() {
        Stream<Object> empty = Stream.empty();
        Stream<Integer> integerStream = Stream.of(1);
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3);
    }

    private static void a_2_demoStreamFromCollection() {
        List<Animal> animals = Arrays.asList(
                new Animal("Lion", 10),
                new Animal("Elephant", 20),
                new Animal("Giraffe", 30)
        );
        System.out.println("-------------------");
        List<Animal> filtered = animals
                .stream()
                .filter((a) -> {
                    System.out.println(a);
                    return a.age > 10;
                })
                .filter((a) -> {
                    System.out.println("22 " + a);
                    return true;
                })
                .collect(Collectors.toList());

        System.out.println("-------------------");
        filtered.forEach(System.out::println);
    }


    private static void a_3_demoStreamFromCollectionError() {
        List<Animal> animals = Arrays.asList(
                new Animal("Lion", 10),
                new Animal("Elephant", 20),
                new Animal("Giraffe", 30)
        );
        System.out.println("-------------------");
        Stream<Animal> stream = animals.stream();

        stream
                .filter((a) -> {
                    System.out.println(a);
                    return a.age > 10;
                })
                .filter((a) -> {
                    System.out.println("22 " + a);
                    return true;
                })
                .collect(Collectors.toList());

        stream
                .filter((a) -> {
                    System.out.println(a);
                    return a.age > 10;
                })
                .filter((a) -> {
                    System.out.println("22 " + a);
                    return true;
                })
                .collect(Collectors.toList());

        System.out.println("-------------------");

    }

    private static void a_4_demoCollectionStream() {
        IntStream.of(1, 2, 3);
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 88);

        Map<Integer, Animal> collect = stream
                .map(id -> new Animal("Name_" + id, id))
                .filter(animal -> animal.age >= 3)
                .limit(3)
                .map(a -> {
                    System.out.println("After limit " + a.age);
                    return a;
                })
//                .collect(Collectors.toMap(
//                        Animal::getAge, Function.identity()
//                ));
                .collect(Collectors.toMap(
                        Animal::getAge, animal -> animal
                ));

        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 88);

        List<Animal> collectfiltered = stream
                .map(id -> new Animal("Name_" + id, id))
                .filter(animal -> animal.age >= 3)
                .map(a -> {
                    System.out.println("No limit " + a.age);
                    return a;
                }).collect(Collectors.toList());


        Map<Integer, Animal> map22 = collectfiltered.subList(0, 3)
                                                    .stream()
                                                    .map(a -> {
                                                             return new AbstractMap.SimpleEntry<>(a.age, a);
                                                         }
                                                    ).collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue
                ));

        System.out.println();

    }

    private static class Animal {
        private String name;
        private int age;

        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
