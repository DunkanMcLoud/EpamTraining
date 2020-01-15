package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.tests;

import org.junit.Before;
import org.junit.Test;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.object_io_handler.ObjectInputHandler;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.object_io_handler.ObjectOutputHandler;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor.InitStorageType;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor.StorageInitor;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.transportation.service.TransportationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor.StorageInitorFactory.getStorageInitor;

public class TestStorageExportAndImport {
        public String pathNameOfTempFIle = "ru/epam/javacore/lesson_13_sax_parser_recursion/homework/tests/testInputOutput.txt";
        public CargoService cargoService;
        public CarrierService carrierService;
        public TransportationService transportationService;
        public Path file = null;


    @Before
    public void setUp() {
        try {
            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.MEMORY);
            storageInitor.initStorage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            file = Files.createTempFile("testInputOutput",".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
        public void testExportImportAndCompareCargos() throws IOException {
        try {
            ObjectOutputHandler exporter = new ObjectOutputHandler(file.toFile());
            exporter.exportCargos();
            ObjectInputHandler importer = new ObjectInputHandler(file.toFile());
            List<Cargo> cargosFromStorage = cargoService.getAll();
            List<Cargo> cargosFromImport = importer.importCargos();
            assertTrue((cargosFromImport.size() == cargosFromStorage.size()));
            for (int i = 0; i < cargosFromImport.size() ; i++) {
                assertEquals(cargosFromImport.get(i), cargosFromImport.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(Files.deleteIfExists(file));;
        }
    }

}
