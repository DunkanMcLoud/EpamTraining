package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.repo.impl;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String SQL_QUERY = "SELECT * FROM CARGO";
        List<Cargo> cargos = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            cargos = new ArrayList<>();
            while (resultSet.next()){
                if (resultSet.getString("ENTITY_TYPE").equalsIgnoreCase("clothers")){
                    ClothersCargo cargo = new ClothersCargo();
                    cargo.setSize(resultSet.getString("CLOTHERS_SIZE"));
                    cargo.setMaterial("CLOTHER_MATERIAL");
                    cargo.setName(resultSet.getString("NAME"));
                    cargo.setId(resultSet.getLong("ID"));
                    cargo.setWeight(resultSet.getInt("WEIGHT"));
                    cargos.add(cargo);
                } else {
                    FoodCargo cargo = new FoodCargo();
                    cargo.setExpirationDate(resultSet.getDate("FOOD_EXPIRATION_DATE"));
                    cargo.setStoreTemperature(resultSet.getInt("FOOD_STORE_TEMPERATURE"));
                    cargo.setName(resultSet.getString("NAME"));
                    cargo.setId(resultSet.getLong("ID"));
                    cargo.setWeight(resultSet.getInt("WEIGHT"));
                    cargos.add(cargo);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cargos;
        }

    @Override
    public int countAll() {
        return 0;
    }

}
