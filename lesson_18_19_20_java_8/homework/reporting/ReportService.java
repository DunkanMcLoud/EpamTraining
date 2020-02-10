package ru.epam.javacore.lesson_18_19_20_java_8.homework.reporting;

import ru.epam.javacore.lesson_18_19_20_java_8.homework.common.business.exception.checked.ReportException;

import java.sql.SQLException;

public interface ReportService {
    void exportData() throws ReportException, SQLException;
}
