package ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.saxInitor;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.epam.javacore.lesson_12_io_nio.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_12_io_nio.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_12_io_nio.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_12_io_nio.homework.carrier.domain.CarrierType;
import ru.epam.javacore.lesson_12_io_nio.homework.common.solutions.utils.JavaUtilDateUtils;
import ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.lesson_12_io_nio.homework.transportation.domain.Transportation;

import java.text.ParseException;
import java.util.*;

public class XmlSaxHandler extends DefaultHandler {

    private Map<String, Cargo> cargosMap;
    private Map<String, Carrier> carrierMap;
    private List<BaseFileInitor.ParsedTransportation> parsedTransportationsList;
    private BaseFileInitor.ParsedTransportation curParsedTransportation;
    private FoodCargo curFoodCargo;
    private ClothersCargo curClothersCargo;
    private Carrier curCarrier;
    private Transportation curTransportation;
    private StringBuilder stringBuilder = new StringBuilder();
    private String id;
    private String cargoRef;
    private String carrierRef;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);
        switch (qName) {
            case "data": {
                break;
            }
            case "cargos": {
                cargosMap = new LinkedHashMap<>();
                break;
            }
            case "transportations": {
                parsedTransportationsList = new ArrayList<>();
                break;
            }
            case "carriers": {
                carrierMap = new LinkedHashMap<>();
                break;
            }
            case "cargo": {
                id = attributes.getValue("id");
                String cargoType = attributes.getValue("type");
                if (cargoType.equalsIgnoreCase("food")) {
                    curFoodCargo = new FoodCargo();
                } else if (cargoType.equalsIgnoreCase("clothers")) {
                    curClothersCargo = new ClothersCargo();
                }
                break;
            }
            case "carrier": {
                curCarrier = new Carrier();
                id = attributes.getValue("id");
                break;
            }
            case "transportation": {
                curParsedTransportation = new BaseFileInitor.ParsedTransportation();
                curTransportation = new Transportation();
                cargoRef = attributes.getValue("cargoref");
                carrierRef = attributes.getValue("carrierref");
                curParsedTransportation.setCargoRef(cargoRef);
                curParsedTransportation.setCarrierRef(carrierRef);
                break;
            }
            case "name": {
                break;
            }
            case "weight": {
                break;
            }
            case "expirationDate": {
                break;
            }
            case "storeTemperature": {
                break;
            }
            case "size": {
                break;
            }
            case "material": {
                break;
            }
            case "type": {
                break;
            }
            case "address": {
                break;
            }

            case "billto": {
                break;
            }
            case "transportationBeginDate": {
                break;
            }
            case "description": {
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();
        switch (qName) {
            case "cargo": {
                if (curFoodCargo == null) {
                    cargosMap.put(id, curClothersCargo);
                    id = null;
                    curClothersCargo = null;
                } else {
                    cargosMap.put(id, curFoodCargo);
                    id = null;
                    curFoodCargo = null;
                }
                break;
            }
            case "carrier": {
                carrierMap.put(id, curCarrier);
                id = null;
                curCarrier = null;
                break;
            }
            case "transportation": {
                curParsedTransportation.setTransportation(curTransportation);
                parsedTransportationsList.add(curParsedTransportation);
                id = null;
                curTransportation = new Transportation();
                curParsedTransportation = null;
                carrierRef = null;
                cargoRef = null;
                break;
            }
            case "name": {
                if (curCarrier != null) {
                    curCarrier.setName(data);
                } else {
                    if (curClothersCargo == null) {
                        curFoodCargo.setName(data);
                    } else {
                        curClothersCargo.setName(data);
                    }
                }
                break;
            }
            case "weight": {
                if (curClothersCargo == null) {
                    curFoodCargo.setWeight(Integer.valueOf(data));
                } else {
                    curClothersCargo.setWeight(Integer.valueOf(data));
                }
                break;
            }
            case "size": {
                curClothersCargo.setSize(data);
                break;
            }
            case "material": {
                curClothersCargo.setMaterial(data);
                break;
            }
            case "expirationDate": {
                try {
                    Date date = JavaUtilDateUtils.valueOf(data);
                    curFoodCargo.setExpirationDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "storeTemperature": {
                curFoodCargo.setStoreTemperature(Integer.valueOf(data));
                break;
            }
            case "type": {
                if (data.equalsIgnoreCase("ship")) {
                    curCarrier.setCarrierType(CarrierType.SHIP);
                }
                if (data.equalsIgnoreCase("plane")) {
                    curCarrier.setCarrierType(CarrierType.PLANE);
                }
                if (data.equalsIgnoreCase("car")) {
                    curCarrier.setCarrierType(CarrierType.CAR);
                }
                break;
            }
            case "address": {
                curCarrier.setAddress(data);
                break;
            }
            case "billTo": {
                curTransportation.setBillTo(data);
                break;
            }
            case "transportationBeginDate": {
                try {
                    curTransportation.setTransportationBeginDate(JavaUtilDateUtils.valueOf(data));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "description": {
                curTransportation.setDescription(data);
                break;
            }

            case "cargos": {
                break;
            }
            case "carriers": {
                break;
            }
            case "transportations": {
                break;
            }


        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        stringBuilder.append(data);
    }


    public Map<String, Cargo> getCargosMap() {
        return cargosMap;
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }

    public List<BaseFileInitor.ParsedTransportation> getParsedTransportationsList() {
        return parsedTransportationsList;
    }
}
