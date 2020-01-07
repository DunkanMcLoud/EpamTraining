package ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.saxInitor;

import org.xml.sax.SAXException;
import ru.epam.javacore.lesson_12_io_nio.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_12_io_nio.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.domain.Transportation;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SaxDataInitor extends BaseFileInitor {
    private static final String FILE = "resources/ru/epam/javacore/lesson_12_io_nio/initdata/xmldata.xml";


    @Override
    public void initStorage() throws InitStorageException, ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        File file = new File(FILE);
        XmlSaxHandler handler = new XmlSaxHandler();
        parser.parse(file,handler);
        Map<String, Cargo> cargoMap = handler.getCargosMap();
        Map<String, Carrier> carrierMap = handler.getCarrierMap();
        List<ParsedTransportation> transportations = handler.getParsedTransportationsList();

        setReferencesBetweenEntities(cargoMap,carrierMap,transportations);
        List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
        persistCargos(cargoMap.values());
        persistCarriers(carrierMap.values());
        persistTransportations(transportationList);
    }
}
