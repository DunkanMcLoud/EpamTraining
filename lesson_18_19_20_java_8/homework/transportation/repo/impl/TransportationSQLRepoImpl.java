package ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.repo.impl;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.database.TransportationSqlManager;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.repo.TransportationRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TransportationSQLRepoImpl implements TransportationRepo {
    @Override
    public Optional<Transportation> findById(Long aLong) {
        return Optional.of(SQLUtils.getById(aLong,"TRANSPORTATION", TransportationSqlManager::mapTransportation));
    }

    @Override
    public void save(Transportation entity) {
        TransportationSqlManager.insertionQuery(entity);
    }

    @Override
    public boolean update(Transportation entity) {
        return TransportationSqlManager.updateTuple(entity);
    }

    @Override
    public boolean deleteById(Long aLong) {
        return SQLUtils.deleteByID(aLong,"TRANSPORTATION");
    }

    @Override
    public List<Transportation> getAll() throws SQLException {
        return SQLUtils.selectWithQuery("SELECT * FROM TRANSPORTATION", TransportationSqlManager::mapTransportation);
    }

    @Override
    public int countAll() throws SQLException {
        return getAll().size();
    }
}
