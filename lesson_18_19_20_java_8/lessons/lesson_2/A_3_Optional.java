package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2;

import java.util.Optional;
import java.util.function.Function;

public class A_3_Optional {

    private static class Animal {
        private String name;
        private Country country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }
    }

    private static class Country {
        private String name;
        private Capital capital;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Capital getCapital() {
            return capital;
        }

        public void setCapital(Capital capital) {
            this.capital = capital;
        }
    }

    private static class Capital {
        private String name;

        public Capital(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    private static void demoWithAnimal_1() {
        Animal animal = new Animal();
        if (animal.country != null) {
            System.out.println(animal.country);
        }
    }

    private static void demoWithAnimal_1_withOptional() {
        Animal animal = new Animal();
        Optional.ofNullable(animal.country).ifPresent(c -> {
            System.out.println(c);
        });
    }

    private static void demoWithAnimal_2() {
        Animal animal = new Animal();
        if (animal.country != null) {
            if (animal.country.capital != null) {
                if (animal.country.capital.name != null) {
                    System.out.println(animal.country.capital.name);
                }
            }
        }
    }

    private static void demoWithAnimal_2_0_Improvement() {
        Animal animal = new Animal();
        String capitalUpperCase = Optional
                .ofNullable(animal.country)
                .map(c -> {
                    return c.name.toUpperCase();
                }).orElse("");
    }

    private static void demoWithAnimal_2_Improvement(Animal animal) {
        // val capital = animal?.country?.capital?.name ?: null;


        String capital2 = Optional.ofNullable(animal.country)
                                  .flatMap(country -> {
                                      return Optional.ofNullable(country.capital);
                                  }).flatMap(capital -> {
                    return Optional.ofNullable(capital.name);
                }).map(captinalName -> {
                    return captinalName;
                }).orElse("");

        String capital3 = Optional.ofNullable(animal.country)
                                  .flatMap(country -> Optional.ofNullable(country.capital))
                                  .flatMap(capital -> Optional.ofNullable(capital.name))
                                  .map(captinalName -> {
                                      System.out.println("sd");
                                      return captinalName;
                                  }).orElse("");

        String capital4 = Optional.ofNullable(animal.country)
                                  .flatMap(country -> Optional.ofNullable(country.capital))
                                  .flatMap(capital -> Optional.ofNullable(capital.name))
                                  .map(Function.identity())
                                  .orElse("");

        String capital5 = Optional.ofNullable(animal.country)
                                  .flatMap(country -> Optional.ofNullable(country.capital))
                                  .flatMap(capital -> Optional.ofNullable(capital.name))
                                  .orElse("");


    }

}