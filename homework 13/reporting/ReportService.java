package ru.epam.javacore.lesson_12_io_nio.homework.reporting;

import ru.epam.javacore.lesson_12_io_nio.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
