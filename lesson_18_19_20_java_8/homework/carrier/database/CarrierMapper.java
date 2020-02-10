package ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.database;

        import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.Carrier;
        import ru.epam.javacore.lesson_18_19_20_java_8.homework.carrier.domain.CarrierType;
        import ru.epam.javacore.lesson_18_19_20_java_8.homework.transportation.database.TransportationSqlManager;

        import java.sql.ResultSet;
        import java.sql.SQLException;

public class CarrierMapper {
    public static Carrier mapCarrier(ResultSet resultSet) throws SQLException {
        Carrier carrier = new Carrier();
        if (resultSet.getString("ENTITY_TYPE").equalsIgnoreCase("plane")){
            carrier.setCarrierType(CarrierType.PLANE);
        } else if(resultSet.getString("ENTITY_TYPE").equalsIgnoreCase("ship")){
            carrier.setCarrierType(CarrierType.SHIP);
        } else if (resultSet.getString("ENTITY_TYPE").equalsIgnoreCase("car")){
            carrier.setCarrierType(CarrierType.CAR);
        }
        carrier.setName(resultSet.getString("NAME"));
        carrier.setAddress(resultSet.getString("ADDRESS"));
        carrier.setId(resultSet.getLong("ID"));
        carrier.setTransportations(TransportationSqlManager.getTransportationsByCarrierId(resultSet.getLong("ID")));
        return carrier;
    }
}
