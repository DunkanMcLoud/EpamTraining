package ru.epam.javacore.lesson_18_19_20_java_8.homework.database.handlers.cargo.table;

public class TableCreator {

    public static String getCargoSchema() {
        String createTable = "CREATE TABLE IF NOT EXISTS  CARGO (\n" +
                "ID                       BIGINT  NOT NULL PRIMARY KEY,\n" +
                "NAME                     VARCHAR(50) NOT NULL,\n" +
                "WEIGHT                   INT NOT NULL,\n" +
                "ENTITY_TYPE              VARCHAR(10) NOT NULL,\n" +
                "CLOTHERS_SIZE            VARCHAR(50),\n" +
                "CLOTHER_MATERIAL         VARCHAR(50),\n" +
                "FOOD_EXPIRATION_DATE     TIMESTAMP,\n" +
                "FOOD_STORE_TEMPERATURE   INT,\n" +
                ");";
        return createTable;
    }
}
