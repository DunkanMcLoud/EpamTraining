package ru.epam.javacore.lesson_14_serialization.homework.storage.initor.fileinitor;

import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.CargoType;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_14_serialization.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_14_serialization.homework.carrier.domain.CarrierType;
import ru.epam.javacore.lesson_14_serialization.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_14_serialization.homework.common.solutions.utils.JavaUtilDateUtils;
import ru.epam.javacore.lesson_14_serialization.homework.transportation.domain.Transportation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TextFileDataInitor extends BaseFileInitor {

    private static final String FILE = "resources/initdata.txt";
    private static final String CARGO_SECTION_LABEL_IN_FILE = "--Cargo section--";
    private static final String CARRIER_SECTION_LABEL_IN_FILE = "--Carrier section--";
    private static final String TRANSPORTATION_SECTION_LABEL_IN_FILE = "--Transportation section--";
    private static Map<String, Cargo> cargoMap = null;
    private static Map<String, Carrier> carrierMap = null;
    private static List<ParsedTransportation> transportations = null;

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            File finalFile = file;

            Runnable cargoGetter = () -> {
                try {
                    parseCargosFromFile(finalFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            Runnable carrierGetter = () -> {
                try {
                    parseCarriersFromFile(finalFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Runnable transGetter = () -> {
                try {
                    parseTransportationsDataFromFile(finalFile);
                } catch (Exception e) {
                }
            };
            Thread t1 = new Thread(cargoGetter);
            Thread t2 = new Thread(carrierGetter);
            Thread t3 = new Thread(transGetter);

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(new File(FILE))) {
            Path tempFile = Files.createTempFile("", "");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }

    private void parseCargosFromFile(File file) throws IOException, ParseException {
        Map<String, Cargo> cargos = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARGO_SECTION_LABEL_IN_FILE);

        for (String cargoStr : fileData) {
            SimpleEntry<String, Cargo> cargoData = parseCargoFromString(cargoStr);
            if (cargoData != null) {
                cargos.put(cargoData.getKey(), cargoData.getValue());
            }
        }
        cargoMap = cargos;
    }

    private SimpleEntry<String, Cargo> parseCargoFromString(String cargoAsStr) throws ParseException {
        String[] cargoData = cargoAsStr.split("\\|");

        if (cargoData.length > 0) {
            int index = 0;
            String id = cargoData[index++].trim();
            CargoType cargoType = CargoType.valueOf(cargoData[index++].trim());
            String name = cargoData[index++].trim();
            int weight = Integer.parseInt(cargoData[index++].trim());

            Cargo cargo;
            if (CargoType.CLOTHERS.equals(cargoType)) {
                ClothersCargo clothersCargo = new ClothersCargo();
                clothersCargo.setSize(cargoData[index++].trim());
                clothersCargo.setMaterial(cargoData[index].trim());
                cargo = clothersCargo;
            } else {
                FoodCargo foodCargo = new FoodCargo();
                foodCargo.setExpirationDate(JavaUtilDateUtils.valueOf(cargoData[index++].trim()));
                foodCargo.setStoreTemperature(Integer.parseInt(cargoData[index].trim()));
                cargo = foodCargo;
            }

            cargo.setName(name);
            cargo.setWeight(weight);

            return new SimpleEntry<>(id, cargo);
        }

        return null;
    }

    private void parseCarriersFromFile(File file) throws IOException {
        Map<String, Carrier> carriers = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARRIER_SECTION_LABEL_IN_FILE);

        for (String carriersStr : fileData) {
            SimpleEntry<String, Carrier> carrierData = parseCarrierFromString(carriersStr);
            if (carrierData != null) {
                carriers.put(carrierData.getKey(), carrierData.getValue());
            }
        }

        carrierMap = carriers;
    }


    private SimpleEntry<String, Carrier> parseCarrierFromString(String carrierAsStr) {
        String[] data = carrierAsStr.split("\\|");

        if (data.length > 0) {
            int index = 0;
            String id = data[index++].trim();
            Carrier carrier = new Carrier();
            carrier.setName(data[index++].trim());
            carrier.setAddress(data[index++].trim());
            carrier.setCarrierType(CarrierType.valueOf(data[index].trim()));

            return new SimpleEntry<>(id, carrier);
        }

        return null;
    }

    private void parseTransportationsDataFromFile(File file)
            throws IOException, ParseException {
        List<ParsedTransportation> result = new ArrayList<>();
        List<String> transportationSection = readSectionInFile(file,
                TRANSPORTATION_SECTION_LABEL_IN_FILE);

        for (String transportationDataStr : transportationSection) {
            result.add(parseTransportationDataFromString(transportationDataStr));
        }
        transportations = result;
    }

    ;

    private ParsedTransportation parseTransportationDataFromString(String transportationDataStr)
            throws ParseException {
        String data[] = transportationDataStr.split("\\|");

        ParsedTransportation result = null;
        if (data.length > 0) {
            result = new ParsedTransportation();
            int index = 0;
            result.setCargoRef(data[index++].trim());
            result.setCarrierRef(data[index++].trim());

            Transportation transportation = new Transportation();
            transportation.setDescription(data[index++].trim());
            transportation.setBillTo(data[index++].trim());
            transportation.setTransportationBeginDate(JavaUtilDateUtils.valueOf(data[index].trim()));
            result.setTransportation(transportation);
        }

        return result;
    }

    private List<String> readSectionInFile(File file, String sectionLabel)
            throws IOException {

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean sectionHasStarted = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (sectionHasStarted) {
                    if (line.isEmpty()) {
                        break;
                    }
                    result.add(line);
                }

                if (!sectionHasStarted && sectionLabel.equals(line)) {
                    sectionHasStarted = true;
                }
            }
        }

        return result;
    }

}