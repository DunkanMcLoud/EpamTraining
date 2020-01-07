package ru.epam.javacore.lesson_12_io_nio.homework.storage.initor;

import org.xml.sax.SAXException;
import ru.epam.javacore.lesson_12_io_nio.homework.common.business.exception.checked.InitStorageException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, ParserConfigurationException, SAXException, IOException;
}
