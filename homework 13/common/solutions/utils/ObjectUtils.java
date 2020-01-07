package ru.epam.javacore.lesson_12_io_nio.homework.common.solutions.utils;

import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.domain.Transportation;

public final class ObjectUtils {
    private static Transportation transportation = new Transportation();
    private static Carrier carrier = new Carrier();

    public static Transportation getNewTransportation() {
        return transportation;
    }

    public static Carrier getNewCarrier() {
        return carrier;
    }
}
