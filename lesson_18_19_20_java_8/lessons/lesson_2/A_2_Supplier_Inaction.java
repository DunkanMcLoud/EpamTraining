package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class A_2_Supplier_Inaction {
    public static void main(String[] args) {

        goAndFight(() -> new Tank(), AirPlane::new);

        goAndFight22( new Tank(), new AirPlane());
    }

    private static void demoWorkWithList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        list.forEach(s -> System.out.println("Hello " + s));
        list.removeIf(s -> s.length() == 3);
        System.out.println();
    }

    private static class AirPlane {
        public AirPlane() {
            System.out.println("Airplane has created");
        }

        public void fight() {
            System.out.println("Plane in action");
        }
    }

    private static class Tank {
        public Tank() {
            System.out.println("Tank has created");
        }

        public void fight() {
            System.out.println("Tank in action");
        }
    }

    private static void createArmy() {
        Tank tank = new Tank();
        AirPlane airPlane = new AirPlane();

        tank.fight();
        airPlane.fight();
    }

    private static void goAndFight(Tank tank, AirPlane airPlane) {
        boolean needTank = false;
        if (needTank) {
            tank.fight();
        }

        airPlane.fight();
    }

    private static void goAndFight(Supplier<Tank> tankProvider,
                                   Supplier<AirPlane> airPlaneProvider) {
        boolean needTank = false;
        if (needTank) {
            Tank tank = tankProvider.get();
            tank.fight();
        }

        airPlaneProvider.get().fight();
    }

    private static void goAndFight22(Tank tank,
                                     AirPlane airPlane) {
        boolean needTank = false;
        if (needTank) {

            tank.fight();
        }

        airPlane.fight();
    }

    private static void warBegin() {
        goAndFight(new Tank(), new AirPlane());
    }
}
