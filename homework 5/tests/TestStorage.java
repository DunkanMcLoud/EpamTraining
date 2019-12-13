package ru.epam.javacore.lesson_4.homework.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.javacore.lesson_4.homework.cargo.Cargo;
import ru.epam.javacore.lesson_4.homework.cargo.CargoClothes;
import ru.epam.javacore.lesson_4.homework.cargo.CargoComputers;
import ru.epam.javacore.lesson_4.homework.cargo.CargoFood;
import ru.epam.javacore.lesson_4.homework.carrier.Carrier;
import ru.epam.javacore.lesson_4.homework.carrier.CarrierType;
import ru.epam.javacore.lesson_4.homework.storage.Storage;
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
        c1 = new CargoFood();
        c2 = new CargoClothes();
        c3 = new CargoComputers();
        c1.setId(); //0
        c1.setName("APPLE");
        c1.setWeight(3);

        c2.setWeight(3);
        c2.setName("Shirt");
        c2.setId(); // 1

        c3.setId(); // 2
        c3.setName("aPPlE");
        c3.setWeight(10000);

        //TODO transportations[]

        //Setting Carriers

        carrier1 = new Carrier();
        carrier2 = new Carrier();
        carrier3 = new Carrier();

        carrier1.setId(); //3
        carrier2.setId(); //4
        carrier3.setId(); //5

        carrier1.setCarrierType(CarrierType.CAR);
        carrier2.setCarrierType(CarrierType.PLANE);
        carrier3.setCarrierType(CarrierType.SHIP);

        carrier1.setAddress("Brighton beach,17");
        carrier2.setAddress("Baker Street,21/b");
        carrier3.setAddress("Moscow,Kremlin");

        carrier1.setName("Jack");
        carrier2.setName("Bekky");
        carrier3.setName("Jeff");

        //TODO transportations[]

        //Setting transportations
        t1 = new Transportation();
        t2 = new Transportation();
        t3 = new Transportation();

        t1.setCargo(c1);
        t2.setCargo(c2);
        t3.setCargo(c3);

        t1.setId();//6
        t2.setId();//7
        t3.setId();//8

        t1.setCarrier(carrier1);
        t2.setCarrier(carrier2);
        t3.setCarrier(carrier3);


        t1.setDate(new Date(2019, 12, 10));
        t2.setDate(new Date(2019, 12, 10));
        t3.setDate(new Date(2019, 12, 10));

        t1.setBillTo("Marvin");
        t2.setBillTo("Bekky");
        t3.setBillTo("Jeff");

        t1.setDescription("Where is power?");
        t2.setDescription("He is on high again");
        t3.setDescription("Moving to Bright Future");
    }

    @Test
    public void testCargoStorage() {
        Storage.addCargo(c1);
        Storage.addCargo(c2);
        Storage.addCargo(c3);
        Storage.displayAllCargo();
    }

    @Test
    public void testCarrierStorage() {
        Storage.addCarrier(carrier1);
        Storage.addCarrier(carrier2);
        Storage.addCarrier(carrier3);
        Storage.displayAllCarriers();
    }

    @Test
    public void testTransportationStorage() {
        Storage.addTransportation(t1);
        Storage.addTransportation(t2);
        Storage.addTransportation(t3);
        Storage.displayAllTransportation();
    }

    @Test
    public void searchbyID(){
        Storage.addCargo(c1);
        Storage.addCargo(c2);
        Storage.addCargo(c3);
        Storage.addCarrier(carrier1);
        Storage.addCarrier(carrier2);
        Storage.addCarrier(carrier3);
        Storage.addTransportation(t1);
        Storage.addTransportation(t2);
        Storage.addTransportation(t3);
        Assert.assertEquals(c1,Storage.getbyID(0));
        Assert.assertEquals(c2,Storage.getbyID(1));

        Assert.assertEquals(c3,Storage.getbyID(2));

        Assert.assertEquals(carrier1,Storage.getbyID(3));

        Assert.assertEquals(carrier2,Storage.getbyID(4));

        Assert.assertEquals(carrier3,Storage.getbyID(5));

        Assert.assertEquals(t1,Storage.getbyID(6));

        Assert.assertEquals(t2,Storage.getbyID(7));

        Assert.assertEquals(t3,Storage.getbyID(8));

    }

    @Test
    public void testSearchByName(){
        Storage.addCargo(c1);
        Storage.addCargo(c2);
        Storage.addCargo(c3);
        Storage.addCarrier(carrier1);
        Storage.addCarrier(carrier2);
        Storage.addCarrier(carrier3);
        Storage.addTransportation(t1);
        Storage.addTransportation(t2);
        Storage.addTransportation(t3);

        System.out.println(Storage.getByName("APPLE"));
        System.out.println(Storage.getByName("Jeff"));
        System.out.println(Storage.getByName("Jack"));

    }


}

