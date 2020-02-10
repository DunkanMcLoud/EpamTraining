package ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.database;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.database.CargoSqlManager;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.database.CarrierMapper;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils.SQLUtils;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.DataSource;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.domain.Transportation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportationSqlManager {
    private static CargoService cargoService = ServiceHolder.getInstance().getCargoService();
    private static CarrierService carrierService = ServiceHolder.getInstance().getCarrierService();

    public static Transportation mapTransportation(ResultSet resultSet) throws SQLException {
        Transportation answer = new Transportation();
        answer.setDescription(resultSet.getString("DESCRIPTION"));
        answer.setTransportationBeginDate(resultSet.getTimestamp("TIMESTAMP"));
        answer.setBillTo(resultSet.getString("BILL_TO"));
        Cargo cargoToSet = SQLUtils.getById(resultSet.getLong("CARGO_ID"), "CARGO", CargoSqlManager::createCargoFromResultSet);
        Carrier carrierToSet = SQLUtils.getById(resultSet.getLong("CARRIER_ID"), "CARRIER", CarrierMapper::mapCarrier);
        answer.setCargo(cargoToSet);
        answer.setCarrier(carrierToSet);
        return answer;
    }

    public static Transportation createTransportation(ResultSet resultSet) throws SQLException {
        Transportation answer = new Transportation();
        answer.setDescription(resultSet.getString("DESCRIPTION"));
        answer.setTransportationBeginDate(resultSet.getTimestamp("BEGIN_DATE"));
        answer.setBillTo(resultSet.getString("BILL_TO"));
        answer.setCarrier(carrierService.getByIdFetchingTransportations(resultSet.getLong("CARRIER_ID")).get());
        answer.setCargo(cargoService.getByIdFetchingTransportations(resultSet.getLong("CARGO_ID")).get());
        return answer;
    }

    public static void insertionQuery(Transportation entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO TRANSPORTATION VALUES (?,?,?,?,?,?)")) {
            int i = 0;
            ps.setLong(++i, entity.getId());
            ps.setLong(++i, entity.getCargo().getId());
            ps.setLong(++i, entity.getCarrier().getId());
            ps.setString(++i, entity.getDescription());
            ps.setString(++i, entity.getBillTo());
            ps.setTimestamp(++i, (Timestamp) entity.getTransportationBeginDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateTuple(Transportation entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLUtils.getStringFromSQLScript("ru/epam/javacore/lesson_23_relational_db/db_config/scripts/transportations/TransportationUpdate.sql"))) {
            int i = 0;
            ps.setLong(++i, entity.getCargo().getId());
            ps.setLong(++i, entity.getCarrier().getId());
            ps.setString(++i, entity.getDescription());
            ps.setString(++i, entity.getBillTo());
            ps.setTimestamp(++i, (Timestamp) entity.getTransportationBeginDate());
            ps.setLong(++i, entity.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }



    public static List<Transportation> getTransportationsByCargoId(Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM TRANSPORTATION WHERE CARGO_ID = ?")) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            List<Transportation> answer = new ArrayList<>();
            while (resultSet.next()) {
                answer.add(TransportationSqlManager.createTransportation(resultSet));
            }
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static List<Transportation> getTransportationsByCarrierId(Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT FROM TRANSPORTATION WHERE CARRIER_ID = ?")) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            List<Transportation> answer = new ArrayList<>();
            while (resultSet.next()) {
                answer.add(TransportationSqlManager.createTransportation(resultSet));
            }
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
