package ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.repo.CargoRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        cargoRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] found = cargoRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }


    @Override
    public void sortCargosByName(SortType type) {
        List<Cargo> cargosToSort = cargoRepo.getAll();
        Collections.sort(cargosToSort, new Comparator<Cargo>() {
            @Override
            public int compare(Cargo cargo, Cargo t1) {
                return cargo.getName().compareTo(t1.getName());
            }
        });
      switch (type){
        case ASC:
          break;
        default:
          Collections.reverse(cargosToSort);
      }
      updateAllCargos(cargosToSort);
    }

    @Override
    public void sortCargosByWeight(SortType type) {
      List<Cargo> cargosToSort=cargoRepo.getAll();
      Collections.sort(cargosToSort, new Comparator<Cargo>() {
        @Override
        public int compare(Cargo cargo, Cargo t1) {
          return Integer.compare(cargo.getWeight(),t1.getWeight());
        }
      });
      switch (type){
        case ASC:
          break;
        default:
          Collections.reverse(cargosToSort);
      }
      updateAllCargos(cargosToSort);
    }

    @Override
    public void sortCargosByNameAndWeight(SortType type) {
      List<Cargo> cargosToSort = cargoRepo.getAll();
      Collections.sort(cargosToSort,new Comparator<Cargo>(){
        @Override
        public int compare(Cargo cargo, Cargo t1) {
          return (cargo.getName().compareTo(t1.getName())+Integer.compare(cargo.getWeight(),t1.getWeight()));
        }
      });
      switch (type){
        case ASC:
          break;
        default:
          Collections.reverse(cargosToSort);
      }
      updateAllCargos(cargosToSort);
    }

    @Override
    public void updateCargoByID(Long id, Cargo cargo) {
        boolean isFound = cargoRepo.deleteById(id);
        if (isFound) {
            if (cargo == null) {
                System.out.println("Cargo Successfully deleted");
            } else {
                cargoRepo.add(cargo);
            }
        } else {
            System.out.println("Not able to find cargo to update. It does not exist");
        }
    }

  @Override
  public void updateAllCargos(List<Cargo> list) {
      cargoRepo.updateAllCargos(list);
  }

  @Override
    public boolean deleteById(Long id) {
        return cargoRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();

        for (Cargo cargo : allCargos) {
            System.out.println(cargo);
        }
    }
}
