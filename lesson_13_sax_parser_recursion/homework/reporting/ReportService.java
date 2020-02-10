package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.reporting;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
