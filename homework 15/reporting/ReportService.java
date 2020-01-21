package ru.epam.javacore.lesson_14_serialization.homework.reporting;

import ru.epam.javacore.lesson_14_serialization.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
