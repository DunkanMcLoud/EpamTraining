package ru.epam.javacore.lesson_11_generics_and_io.homework.storage.exporter;

import ru.epam.javacore.lesson_11_generics_and_io.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_11_generics_and_io.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_11_generics_and_io.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.service.TransportationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StorageFileExporter {
    private static String FILE_PATH = "resources/ru/epam/javacore/lesson_11_generics_and_io/storageWriter.txt";
    private static CargoService cargoService = ServiceHolder.getInstance().getCargoService();
    private static CarrierService carrierService = ServiceHolder.getInstance().getCarrierService();
    private static TransportationService transportationService = ServiceHolder.getInstance().getTransportationService();
    private static String SEPARATOR = ("-----------------"+"\n");

    public static void writeStorageInFile() {
        try {
            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
            writer.write("ALL CARGOS \n");
            for (Cargo cargo : cargoService.getAll()) {
                writer.write(cargo.toString()+"\n");
            }
            writer.write(SEPARATOR);
            writer.write("ALL CARRIERS \n");
            for (Carrier carrier : carrierService.getAll()) {
                writer.write(carrier.toString()+"\n");
            }
            writer.write(SEPARATOR);
            writer.write("ALL TRANSPORTATIONS \n");
            for (Transportation transportation : transportationService.getAll()) {
                writer.write(transportation.toString()+"\n");
            }
            writer.write(SEPARATOR);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
