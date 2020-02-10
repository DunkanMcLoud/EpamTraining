package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_1.a_4_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class A_4_Demo_EmbeddedFunctInterfaces {

    private static class Animal {
        private String name;
        private boolean wild;
        private int age;


        public Animal(String name, boolean wild, int age) {
            this.wild = wild;
            this.age = age;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isWild() {
            return wild;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    ", wild=" + wild +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(
                new Animal("Tiger", true, 22),
                new Animal("Lion", true, 32),
                new Animal("Cat", false, 42),
                new Animal("Cow", false, 52)
        );

        System.out.println(
                filterAndGetAnimalsOlderThan(animals, 33)
        );
        System.out.println("---------");
        System.out.println(
                filterAndGetAnimalsWithNameStartingWithCLetter(animals)
        );
        System.out.println("---------");
        System.out.println(
                filterAndGetWildAnimal(animals)
        );

        filterAnimals(animals, (Animal a) -> {
            return a.isWild();
        });

        filterAnimals(animals, Animal::isWild);
        filterAnimals(animals, (Animal a) -> a.getName().startsWith("C"));

        filterAnimals(animals, (Animal a) -> a.getAge() > 33);

        int age = 33;
        filterAnimals(animals, age,
                      (animalAge,animal)-> animal.getAge() > animalAge);
    }

    private static List<Animal> filterAnimals(List<Animal> animals, int age,
                                              BiPredicate<Integer, Animal> condition) {

        List<Animal> result = new ArrayList<>();
        animals.forEach(a -> {
            if (condition.test(age, a)) {
                result.add(a);
            }
        });

        return result;

    }

    private static List<Animal> filterAnimals(List<Animal> animals,
                                              Predicate<Animal> condition) {

        List<Animal> result = new ArrayList<>();
        animals.forEach(a -> {
            if (condition.test(a)) {
                result.add(a);
            }
        });

        return result;

    }

    private static List<Animal> filterAndGetWildAnimal(List<Animal> animals) {
        List<Animal> result = new ArrayList<>();
        animals.forEach(a -> {
            if (a.isWild()) {
                result.add(a);
            }
        });

        return result;
    }

    private static List<Animal> filterAndGetAnimalsWithNameStartingWithCLetter(List<Animal> animals) {
        List<Animal> result = new ArrayList<>();
        animals.forEach(a -> {
            if (a.getName().startsWith("C")) {
                result.add(a);
            }
        });

        return result;
    }

    private static List<Animal> filterAndGetAnimalsOlderThan(List<Animal> animals, int age) {
        List<Animal> result = new ArrayList<>();
        animals.forEach(a -> {
            if (a.getAge() > age) {
                result.add(a);
            }
        });

        return result;
    }
}
