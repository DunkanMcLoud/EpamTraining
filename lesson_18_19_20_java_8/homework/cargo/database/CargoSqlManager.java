package ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.database;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.DataSource;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.database.TransportationSqlManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class CargoSqlManager {
    public static Cargo createCargoFromResultSet(ResultSet resultSet) throws Exception {
        if (resultSet.getString("ENTITY_TYPE").equalsIgnoreCase("clothers")) {
            ClothersCargo cargo = new ClothersCargo();
            cargo.setSize(resultSet.getString("CLOTHERS_SIZE"));
            cargo.setMaterial("CLOTHER_MATERIAL");
            cargo.setName(resultSet.getString("NAME"));
            cargo.setId(resultSet.getLong("ID"));
            cargo.setWeight(resultSet.getInt("WEIGHT"));
            cargo.setTransportations(TransportationSqlManager.getTransportationsByCargoId(resultSet.getLong("ID")));
            return cargo;
        } else {
            FoodCargo cargo = new FoodCargo();
            cargo.setExpirationDate(resultSet.getDate("FOOD_EXPIRATION_DATE"));
            cargo.setStoreTemperature(resultSet.getInt("FOOD_STORE_TEMPERATURE"));
            cargo.setName(resultSet.getString("NAME"));
            cargo.setId(resultSet.getLong("ID"));
            cargo.setWeight(resultSet.getInt("WEIGHT"));
            cargo.setTransportations(TransportationSqlManager.getTransportationsByCargoId(resultSet.getLong("ID")));
            return cargo;
        }
    }

    public static boolean updateTuple(Cargo entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLUtils.getStringFromSQLScript("ru/epam/javacore/lesson_23_relational_db/db_config/scripts/transportations/TransportationUpdate.sql"))) {
            int i = 0;
            ps.setString(++i, entity.getName());
            ps.setInt(++i, entity.getWeight());
            ps.setString(++i, entity.getCargoType().toString());
            ps.setString(++i, entity.getClass().getDeclaredField("size").toString());
            ps.setString(++i,entity.getClass().getDeclaredField("material").toString());
            ps.setTimestamp(++i, (Timestamp) Class.forName("FoodCargo").getDeclaredField("storeTemperature").get(entity));
            ps.setInt(++i,Class.forName("FoodCargo").getDeclaredField("storeTemperature").getInt(entity));
            ps.setLong(++i, entity.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


}

