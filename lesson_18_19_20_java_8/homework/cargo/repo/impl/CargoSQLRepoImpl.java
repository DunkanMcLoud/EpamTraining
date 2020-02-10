package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.CargoRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.database.CargoSqlManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoSQLRepoImpl implements CargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public List<Cargo> findByName(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return null;
    }

    @Override
    public Optional<Cargo> findById(Long aLong) {
        Cargo cargo = SQLUtils.getById(aLong,"CARGO", CargoSqlManager::createCargoFromResultSet);
        return Optional.of(cargo);
    }

    @Override
    public void save(Cargo entity) {

    }

    @Override
    public boolean update(Cargo entity) {
        return CargoSqlManager.updateTuple(entity);
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Cargo> getAll() throws SQLException {
        List<Cargo> cargos = SQLUtils.selectWithQuery("SELECT * FROM CARGO;", CargoSqlManager::createCargoFromResultSet);
        return cargos;
    }

    @Override
    public int countAll() throws SQLException {
        return getAll().size();
    }
}
