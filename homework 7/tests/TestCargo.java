package tests;

import cargo.domain.Cargo;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import cargo.repo.CargoRepo;
import cargo.repo.impl.CargoCollectionRepoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCargo {
    Cargo cargo1;
    Cargo cargo2;
    Cargo cargo3;
    Cargo cargo4;
    Cargo cargo5;
    CargoRepo testRepo = new CargoCollectionRepoImpl();


    @Before
    public void setUp() {
        cargo1 = new FoodCargo();
        cargo2 = new FoodCargo();
        cargo3 = new FoodCargo();
        cargo4 = new FoodCargo();
        cargo5 = new ClothersCargo();
        cargo1.setName("Melon");
        cargo2.setName("melon");
        cargo3.setName("melON ");
        cargo4.setName("Ketchup");
        cargo5.setName("T-shirt");

        cargo1.setWeight(2000);
        cargo2.setWeight(3000);
        cargo3.setWeight(300);
        cargo4.setWeight(500);
        cargo5.setWeight(100);

        testRepo.add(cargo1);
        testRepo.add(cargo2);
        testRepo.add(cargo3);
        testRepo.add(cargo4);
        testRepo.add(cargo5);
    }

    @Test
    public void test_getById(){
        Assert.assertEquals(testRepo.getById(1),cargo1);
        Assert.assertEquals(testRepo.getById(2),cargo2);
        Assert.assertEquals(testRepo.getById(3),cargo3);
        Assert.assertEquals(testRepo.getById(4),cargo4);
        Assert.assertEquals(testRepo.getById(5),cargo5);
    }

    @Test
    public void test_getByName(){
        Assert.assertEquals(testRepo.getByName("MELON"),new Cargo[]{cargo1,cargo2,cargo3});
    }

    @Test
    public void test_deleteByID(){
        testRepo.deleteById(1);
        testRepo.deleteById(2);
        testRepo.deleteById(3);
        testRepo.displayAllCargosInCollection();
    }


}
