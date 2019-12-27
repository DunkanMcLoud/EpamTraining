package ru.epam.javacore.lesson_10_generics.homework.storage.outsource_initor;

import ru.epam.javacore.lesson_10_generics.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_10_generics.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_10_generics.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_10_generics.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_10_generics.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_10_generics.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_10_generics.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_10_generics.homework.transportation.service.TransportationService;
import ru.epam.javacore.lesson_10_generics.homework.carrier.domain.Carrier;

import java.io.*;
import java.util.Date;

public class OutSrcFileInitor {

    private static CarrierService carrierService;
    private static CargoService cargoService;
    private static TransportationService transportationService;
    private static final String FILE_PATH = "/home/dunkan/Рабочий стол/EPAM homework/epamjavacore/src/ru/epam/javacore/lesson_10_generics/homework/storage/outsource_initor/Storage";
    private static BufferedReader bufferedReader;

    static {
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(FILE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        initStorage();
        String line;
        line = bufferedReader.readLine();
        System.out.println("Reading line "+ line);
        parseLines(line);
        cargoService.printAll();
        carrierService.printAll();
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

    public static void initStorage() {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

    }

    private static void initCargos() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] composition = line.split(":", 4);
                composition = removeSpacesFromComposition(composition);
                if (composition[0].equalsIgnoreCase("Clothes")) {
                    createClothesCargo(composition);
                    line=bufferedReader.readLine();
                } else {
                    createFoodCargo(composition);
                    line=bufferedReader.readLine();
                }
            }
            line = bufferedReader.readLine();
            parseLines(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] removeSpacesFromComposition(String[] composition) {
        String[] newComposition = new String[composition.length];
        int index = 0;
        for (String str:
             composition) {
            newComposition[index++]=str.strip();
        }
        return newComposition;
    }

    private static void createFoodCargo(String[] composition) {
        FoodCargo cargoToAdd = new FoodCargo();
        Date dateToSet = createDateFromString(composition[1]);
        String nameToSet = composition[2];
        cargoToAdd.setName(nameToSet);
        cargoToAdd.setExpirationDate(dateToSet);
        cargoToAdd.setId(IdGenerator.generateId());
        cargoService.save(cargoToAdd);
    }

    private static Date createDateFromString(String s) {
        String[] data = s.split("\\s",3);
        data = removeSpacesFromComposition(data);
        Date date = new Date(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
        return date;

    }

    private static void createClothesCargo(String[] composition) {
        ClothersCargo cargoToAdd = new ClothersCargo();
        String size = (composition[1]);
        String name = composition[2];
        Integer weight = Integer.parseInt(composition[3]);
        cargoToAdd.setSize(size);
        cargoToAdd.setName(name);
        cargoToAdd.setWeight(weight);
        cargoToAdd.setId(IdGenerator.generateId());
        cargoService.save(cargoToAdd);
    }

    private static void initCarriers() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] carrierComposition = line.split(":", 2);
                carrierComposition = removeSpacesFromComposition(carrierComposition);
                createCarrier(carrierComposition);
                line=bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            parseLines(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCarrier(String[] composition) {
        Carrier carrierToAdd = new Carrier();
        String name = composition[0];
        String address = composition[1];
        carrierToAdd.setName(name);
        carrierToAdd.setAddress(address);
        carrierToAdd.setId(IdGenerator.generateId());
        carrierService.save(carrierToAdd);
    }

    private static void initTransportations() {
        try {
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("--end--")) {
                String[] composition = line.split("|");
                createTransportation(composition);
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
