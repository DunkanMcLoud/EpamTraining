package ru.epam.javacore.lesson_4.homework.tests;

import org.junit.Before;
import org.junit.Test;
import ru.epam.javacore.lesson_4.homework.Storage.Storage;
import ru.epam.javacore.lesson_4.homework.cargo.Cargo;
import ru.epam.javacore.lesson_4.homework.cargo.CargoType;
import ru.epam.javacore.lesson_4.homework.carrier.Carrier;
import ru.epam.javacore.lesson_4.homework.carrier.CarrierType;
import ru.epam.javacore.lesson_4.homework.transportation.Transportation;

import java.util.Date;

public class TestStorage {
    Cargo c1;
    Cargo c2;
    Cargo c3;
    Carrier carrier1;
    Carrier carrier2;
    Carrier carrier3;
    Transportation t1;
    Transportation t2;
    Transportation t3;


    @Before
    public void setUp3_cargos_carriers_transportations() {
        //Setting Cargos
        c1 = new Cargo();
        c2 = new Cargo();
        c3 = new Cargo();
        c1.setCargoType(CargoType.FOOD);
        c1.setId(1L);
        c1.setName("Vodka");
        c1.setWeight(3);

        c2.setCargoType(CargoType.CLOTHES);
        c2.setWeight(3);
        c2.setName("Astronaut Suite");
        c2.setId(2L);

        c3.setId(3L);
        c3.setCargoType(CargoType.COMPUTERS);
        c3.setName("IPhuck");
        c3.setWeight(10000);

        //TODO transportations[]

        //Setting Carriers

        carrier1 = new Carrier();
        carrier2 = new Carrier();
        carrier3 = new Carrier();

        carrier1.setId(1L);
        carrier2.setId(2L);
        carrier3.setId(3L);

        carrier1.setCarrierType(CarrierType.CAR);
        carrier2.setCarrierType(CarrierType.PLANE);
        carrier3.setCarrierType(CarrierType.SHIP);

        carrier1.setAddress("Brighton beach,17");
        carrier2.setAddress("Baker Street,21/b");
        carrier3.setAddress("Moscow,Kremlin");

        carrier1.setName("Danila Bagrov");
        carrier2.setName("John Watson");
        carrier3.setName("Mr Vladimir Putin");

        //TODO transportations[]

        //Setting transportations
        t1 = new Transportation();
        t2 = new Transportation();
        t3 = new Transportation();

        t1.setCargo(c1);
        t2.setCargo(c2);
        t3.setCargo(c3);

        t1.setId(1L);
        t2.setId(2L);
        t3.setId(3L);

        t1.setCarrier(carrier1);
        t2.setCarrier(carrier2);
        t3.setCarrier(carrier3);


        t1.setDate(new Date(2019,12,10));
        t2.setDate(new Date(2019,12,10));
        t3.setDate(new Date(2019,12,10));

        t1.setBillTo("Mr Mannys");
        t2.setBillTo("Queen Elizabeth 2nd");
        t3.setBillTo("Kompot");

        t1.setDescription("Where is power?");
        t2.setDescription("He is on high again");
        t3.setDescription("Moving to Bright Future");
    }

    @Test
    public void testCargoStorage(){
        Storage.addCargo(c1);
        Storage.addCargo(c2);
        Storage.addCargo(c3);
        Storage.displayAllCargo();
    }

    @Test
    public void testCarrierStorage(){
        Storage.addCarrier(carrier1);
        Storage.addCarrier(carrier2);
        Storage.addCarrier(carrier3);
        Storage.displayAllCarriers();
    }

    @Test
    public void testTransportationStorage(){
        Storage.addTransportation(t1);
        Storage.addTransportation(t2);
        Storage.addTransportation(t3);
        Storage.displayAllTransportation();
    }




}

