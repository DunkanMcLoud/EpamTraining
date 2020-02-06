package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;

import java.util.List;
import java.util.Optional;

public class CargoDatabaseRepoImplementation extends CommonCargoRepo {

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return Optional.empty();
    }

    @Override
    public List<Cargo> findByName(String name) {
        return null;
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return null;
    }

    @Override
    public Optional<Cargo> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Cargo entity) {

    }

    @Override
    public boolean update(Cargo entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Cargo> getAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }

}
