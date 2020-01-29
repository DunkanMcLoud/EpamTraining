package ru.epam.javacore.lesson_16_concurrency.homework.reporting;

import ru.epam.javacore.lesson_16_concurrency.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
