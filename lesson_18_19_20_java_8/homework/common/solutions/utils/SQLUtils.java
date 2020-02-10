package ru.epam.javacore.lesson_18_19_20_java_8.homework.common.solutions.utils;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.connection.DataSource;
import ru.epam.javacore.lesson_18_19_20_java_8.homework.database.mappers.JdbcFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


public class SQLUtils {
    public static String getStringFromSQLScript(String path) throws IOException {
        List<String> listOfStr = Files.readAllLines(Paths.get(path));
        StringBuilder builder = new StringBuilder();
        listOfStr.stream().forEach((element) -> builder.append(element));
        String result = builder.toString();
        return result;
    }

    public static <Entity> List<Entity> selectWithQuery(String sqlQuery, JdbcFunction<ResultSet, Entity> rsConverter) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = ps.executeQuery();
            List<Entity> answer = new ArrayList<>();
            while (resultSet.next()) {
                Entity entity = (rsConverter.apply(resultSet));
                answer.add(entity);
            }
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <Entity> Entity getById(Long id, String tableName, JdbcFunction<ResultSet, Entity> rsConverter) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName +" WHERE ID=?;")) {
            int index = 0;
            ps.setLong(++index, id);
            ResultSet resultSet = ps.executeQuery();
            Entity answer = rsConverter.apply(resultSet);
            return answer;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static boolean deleteByID(Long id, String tableName) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(getStringFromSQLScript("ru/epam/javacore/lesson_23_relational_db/db_config/scripts/deleteById.sql"))) {
            int i = 0;
            ps.setString(++i, tableName);
            ps.setLong(++i, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}

