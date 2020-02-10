package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.impl;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.database.CarrierMapper;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarrierSQLRepoImpl implements CarrierRepo {
    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public List<Carrier> findByName(String name) {
        return SQLUtils.selectWithQuery("SELECT * FROM CARRIER WHERE NAME ="  + name+";", CarrierMapper::mapCarrier);
    }

    @Override
    public Optional<Carrier> findById(Long aLong) {
        return Optional.of(SQLUtils.getById(aLong,"CARRIER",CarrierMapper::mapCarrier));
    }

    @Override
    public void save(Carrier entity) {

    }

    @Override
    public boolean update(Carrier entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Carrier> getAll() throws SQLException {
        return SQLUtils.selectWithQuery("SELECT * FROM CARRIER", CarrierMapper::mapCarrier);
    }

    @Override
    public int countAll() throws SQLException {
        return getAll().size();
    }
}
