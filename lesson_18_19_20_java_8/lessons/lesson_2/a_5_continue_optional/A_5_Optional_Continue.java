package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2.a_5_continue_optional;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class A_5_Optional_Continue {

    public static void main(String[] args) {
      /*  Map<String, Integer> salaryByEmployee = new HashMap<>();
        salaryByEmployee.put("Yusupov", 100000000);
        salaryByEmployee.put("Ivanov", 100000000);
        increaseSalary_1(salaryByEmployee);
        */

        demoOptional_2();
        System.out.println("---------");
        demoOptional_3();
    }

    private static void increaseSalary_1(Map<String, Integer> salaryByEmployee) {
        Set<String> bestEmployees = new HashSet<>();
        bestEmployees.add("Yusupov");
        bestEmployees.add("ivanov");

        for (String employee : bestEmployees) {
            Optional<Integer> integer = Optional
                    .ofNullable(salaryByEmployee.get(employee));
            if (integer.isPresent()) {
                Integer integer1 = integer.get();
                System.out.println(++integer1);
            }
        }
    }

    private static void demoOptional_2() {
        Integer discount1 = getDiscount1(1).orElse(10);
        System.out.println(discount1);
        Integer discount2 = getDiscount2(1).orElse(getDefaultDiscaunt());
        System.out.println(discount2);
    }

    private static void demoOptional_3() {
        Integer discount1 = getDiscount1(1).orElseGet(() -> 10);
        System.out.println(discount1);
        Integer discount2 = getDiscount2(1).orElseGet(() -> getDefaultDiscaunt());
        System.out.println(discount2);
    }

    private static int getDefaultDiscaunt(){
        System.out.println("getDefaultDiscaunt ");
        return 10;
    }

    private static Optional<Integer> getDiscount1(long id) {
        return Optional.empty();
    }

    private static Optional<Integer> getDiscount2(long id) {
        return Optional.of(23);
    }
}
