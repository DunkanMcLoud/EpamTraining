package ru.epam.javacore.lesson_10_generics.homework.storage.initor;

import ru.epam.javacore.lesson_10_generics.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_10_generics.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_10_generics.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_10_generics.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_10_generics.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_10_generics.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_10_generics.homework.storage.initor.StorageInitor;
import ru.epam.javacore.lesson_10_generics.homework.transportation.service.TransportationService;
import ru.epam.javacore.lesson_10_generics.homework.carrier.domain.Carrier;

import java.io.*;
import java.util.Date;

public class OutSrcFileInitor implements StorageInitor {

    private static CarrierService carrierService;
    private static CargoService cargoService;
    private static TransportationService transportationService;
    private String FILE_PATH;
    private static BufferedReader bufferedReader;

    public OutSrcFileInitor(String path){
        this.FILE_PATH = path;
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(this.FILE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseLines(String line) {
        if (line == null) {
            System.out.println("Parsing finished!");
        } else if (line.equalsIgnoreCase("--Cargos--")) {
            System.out.println("Initing Cargos");
            initCargos();
        } else if (line.equalsIgnoreCase("--Carrier--")) {
            initCarriers();
        } else if (line.equalsIgnoreCase("--Transportations--")) {
            System.out.println("Reached transportations");
            initTransportations();
        }
    }

    public void initStorage() {
        String line;
        try {
            line = bufferedReader.readLine();
            System.out.println("Reading line " + line);
            parseLines(line);
            System.out.println("Closing reader!");
            bufferedReader.close();
            System.out.println("Reader Closed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    private static void initCargos() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] cargoParticles = line.split(":", 4);
                cargoParticles = removeSpacesFromParticles(cargoParticles);
                if (cargoParticles[0].equalsIgnoreCase("Clothes")) {
                    createClothesCargo(cargoParticles);
                    line = bufferedReader.readLine();
                } else {
                    createFoodCargo(cargoParticles);
                    line = bufferedReader.readLine();
                }
            }
            line = bufferedReader.readLine();
            parseLines(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] removeSpacesFromParticles(String[] arrayOfParticles) {
        String[] newArrayOfParts = new String[arrayOfParticles.length];
        int index = 0;
        for (String str :
                arrayOfParticles) {
            newArrayOfParts[index++] = str.strip();
        }
        return newArrayOfParts;
    }

    private static void createFoodCargo(String[] arrayOfParticles) {
        FoodCargo cargoToAdd = new FoodCargo();
        Date dateToSet = createDateFromString(arrayOfParticles[1]);
        String nameToSet = arrayOfParticles[2];
        cargoToAdd.setName(nameToSet);
        cargoToAdd.setExpirationDate(dateToSet);
        cargoService.save(cargoToAdd);
    }

    private static Date createDateFromString(String s) {
        String[] data = s.split("\\s", 3);
        data = removeSpacesFromParticles(data);
        Date date = new Date(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
        return date;

    }

    private static void createClothesCargo(String[] arrayOfParticles) {
        ClothersCargo cargoToAdd = new ClothersCargo();
        String size = (arrayOfParticles[1]);
        String name = arrayOfParticles[2];
        Integer weight = Integer.parseInt(arrayOfParticles[3]);
        cargoToAdd.setSize(size);
        cargoToAdd.setName(name);
        cargoToAdd.setWeight(weight);
        cargoService.save(cargoToAdd);
    }

    private static void initCarriers() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] carrierArrayOfParts = line.split(":", 2);
                carrierArrayOfParts = removeSpacesFromParticles(carrierArrayOfParts);
                createCarrier(carrierArrayOfParts);
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            parseLines(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCarrier(String[] arrayOfParts) {
        Carrier carrierToAdd = new Carrier();
        String name = arrayOfParts[0];
        String address = arrayOfParts[1];
        carrierToAdd.setName(name);
        carrierToAdd.setAddress(address);
        carrierService.save(carrierToAdd);
    }

    private static void initTransportations() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] transportationParts = line.split("|");
                createTransportation(transportationParts);
                System.out.println("Finish this method , prick");
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            parseLines(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTransportation(String[] composition) {
        System.out.println("Go back to School");
    }

    private void appendTransportationsToCargos() {

    }
}
