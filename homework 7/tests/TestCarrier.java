package tests;

import cargo.domain.Cargo;
import cargo.repo.CargoRepo;
import cargo.repo.impl.CargoCollectionRepoImpl;
import carrier.domain.Carrier;
import carrier.domain.CarrierType;
import carrier.repo.CarrierRepo;
import carrier.repo.impl.CarrierCollectionImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCarrier {
    public Carrier carrier1;
    public Carrier carrier2;
    public Carrier carrier3;
    public CarrierRepo testCarrierRepo = new CarrierCollectionImpl();

    @Before
    public void setUp(){
        carrier1 = new Carrier();
        carrier2 = new Carrier();
        carrier3 = new Carrier();

        carrier1.setName("Mike");
        carrier2.setName("MIKE");
        carrier3.setName("John");

        carrier1.setCarrierType(CarrierType.CAR);
        carrier2.setCarrierType(CarrierType.PLANE);
        carrier3.setCarrierType(CarrierType.TRAIN);

        carrier1.setAddress("India");
        carrier2.setAddress("Russia");
        carrier3.setAddress("USA");

        testCarrierRepo.add(carrier1);
        testCarrierRepo.add(carrier2);
        testCarrierRepo.add(carrier3);
    }

    @Test
    public void test_getById(){
        Assert.assertEquals(testCarrierRepo.getById(1),carrier1);
        Assert.assertEquals(testCarrierRepo.getById(2),carrier2);
        Assert.assertEquals(testCarrierRepo.getById(3),carrier3);
    }

    @Test
    public void test_getByName(){
        Assert.assertEquals(testCarrierRepo.getByName("Mike"),new Carrier[]{carrier1,carrier2});
    }

    @Test
    public void test_deleteByID(){
        testCarrierRepo.deleteById(1);
        testCarrierRepo.deleteById(2);
        testCarrierRepo.deleteById(3);
        Assert.assertEquals(testCarrierRepo.getByName("Mike"),null);
    }
}
