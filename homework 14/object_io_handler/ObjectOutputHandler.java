package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.object_io_handler;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.transportation.service.TransportationService;

import java.io.*;

public class ObjectOutputHandler {
    private static CargoService cargoService = ServiceHolder.getInstance().getCargoService();
    private static TransportationService transportationService = ServiceHolder.getInstance().getTransportationService();
    private static CarrierService carrierService = ServiceHolder.getInstance().getCarrierService();
    private File fileToExportData;


    public ObjectOutputHandler(File file) throws Exception {
        fileToExportData = file;
    }


    public void exportStorage() throws IOException {
        exportCargos();
        exportCarriers();
        exportTransportations();
    }

    public void exportCargos() throws IOException {
        try (ObjectOutput objectOutput = new ObjectOutputStream((new FileOutputStream(fileToExportData)))) {
            for (Cargo cargo : cargoService.getAll()) {
                objectOutput.writeObject(cargo);
            }
        }
    }

    public void exportCarriers() throws IOException {
        try (ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileToExportData))) {
            for (Carrier carrier : carrierService.getAll()) {
                objectOutput.writeObject(carrier);
            }
        }
    }

    public void exportTransportations() throws IOException{
        try (ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileToExportData))) {
            for (Transportation transportation : transportationService.getAll()) {
                objectOutput.writeObject(transportation);
            }
        }
    }

}
