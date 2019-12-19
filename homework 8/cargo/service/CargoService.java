package ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.CargoType;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {

    enum SortType{
        ASC,DESC
    }

    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> getByName(String name);

    void sortCargosByName(SortType type);

    void sortCargosByWeight(SortType type);

    void sortCargosByNameAndWeight(SortType type);

    void updateCargoByID(Long id,Cargo cargo);

    void updateAllCargos(List<Cargo> list);
}
