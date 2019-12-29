package ru.epam.javacore.lesson_11_generics_and_io.homework.storage.initor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.epam.javacore.lesson_11_generics_and_io.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_11_generics_and_io.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_11_generics_and_io.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_11_generics_and_io.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_11_generics_and_io.homework.carrier.domain.CarrierType;
import ru.epam.javacore.lesson_11_generics_and_io.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_11_generics_and_io.homework.transportation.domain.Transportation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class XML_initor implements StorageInitor {
    private static final String xml_path = "resources/ru/epam/javacore/lesson_11_generics_and_io/CargoTracker.xml";
    private File file;
    private Document doc;

    @Override
    public void initStorage() throws InitStorageException {
        Map<String, Cargo> cargoMap = parseCargosFromDoc(doc);
        Map<String, Carrier> carrierMap = parseCarriersFromDoc(doc);
        List<ParsedTransportation> transportations = parseTransportationsDataFromDoc(doc);
        setReferencesBetweenEntities(cargoMap, carrierMap, transportations);
        List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
        persistTransportations(transportationList);
        persistCargos(cargoMap.values());
        persistCarriers(carrierMap.values());
    }

    public XML_initor() {
        try {
            file = new File(xml_path);
            doc = getDocumentFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Transportation> getTransportationsFromParsedObject(List<ParsedTransportation> transportations) {
        List<Transportation> result = new ArrayList<>();
        for (ParsedTransportation parsedTransportation : transportations) {
            result.add(parsedTransportation.getTransportation());
        }
        return result;
    }


    private void setReferencesBetweenEntities(Map<String, Cargo> cargoMap, Map<String, Carrier> carrierMap, List<ParsedTransportation> transportations) {
        for (ParsedTransportation parsedTransportation : transportations) {
            String cargoRef = parsedTransportation.getCargoRef();
            String carrierRef = parsedTransportation.getCarrierRef();
            Transportation transportation = parsedTransportation.getTransportation();
            transportation.setCargo(cargoMap.get(cargoRef));
            transportation.setCarrier(carrierMap.get(carrierRef));

            List<Transportation> transList = (ArrayList<Transportation>) cargoMap.get(cargoRef).getTransportations();
            if (transList != null) {
                transList.add(transportation);
                cargoMap.get(cargoRef).setTransportations(transList);
            } else {
                transList = new ArrayList<>();
                transList.add(transportation);
                cargoMap.get(cargoRef).setTransportations(transList);
            }
        }
    }

    private List<ParsedTransportation> parseTransportationsDataFromDoc(Document doc) {
        List<ParsedTransportation> listToReturn = new ArrayList<>();
        Node root = doc.getElementsByTagName("root").item(0);
        NodeList transportations = ((Element) root).getElementsByTagName("Transportation");
        int lenOfParsedList = transportations.getLength();
        for (int i = 0; i < lenOfParsedList; i++) {
            listToReturn.add(createParsedTransportationFromNode(transportations.item(i)));
        }
        return listToReturn;
    }

    private ParsedTransportation createParsedTransportationFromNode(Node item) {
        Transportation transportation = new Transportation();
        String cargoRef = ((Element) item).getElementsByTagName("cargoToTransport").item(0).getTextContent();
        String carrierRef = ((Element) item).getElementsByTagName("carrierUsed").item(0).getTextContent();
        String description = ((Element) item).getElementsByTagName("description").item(0).getTextContent();
        String billTo = ((Element) item).getElementsByTagName("billTo").item(0).getTextContent();
        Date date = yieldDateFromString(((Element) item).getElementsByTagName("beginDate").item(0).getTextContent());
        transportation.setDescription(description);
        transportation.setBillTo(billTo);
        transportation.setTransportationBeginDate(date);
        return new ParsedTransportation(cargoRef, carrierRef, transportation);
    }

    private class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;

        private String getCargoRef() {
            return cargoRef;
        }

        private String getCarrierRef() {
            return carrierRef;
        }

        private Transportation getTransportation() {
            return transportation;
        }

        protected ParsedTransportation(String cargoRef, String carrierRef, Transportation transportation) {
            this.cargoRef = cargoRef;
            this.carrierRef = carrierRef;
            this.transportation = transportation;
        }

    }


    private Map<String, Carrier> parseCarriersFromDoc(Document doc) {
        Map<String, Carrier> carriers = new LinkedHashMap<>();
        Node root = doc.getElementsByTagName("root").item(0);
        NodeList allCarriers = ((Element) root).getElementsByTagName("Carrier");
        int length = allCarriers.getLength();
        for (int i = 0; i < length; i++) {
            Node carrierNode = allCarriers.item(i);
            SimpleEntry<String, Carrier> entry = createCarrierFromXMLNode(carrierNode);
            if (entry != null) {
                carriers.put(entry.getKey(), entry.getValue());
            }
        }
        return carriers;
    }

    private SimpleEntry<String, Carrier> createCarrierFromXMLNode(Node carrierNode) {
        String type = ((Element) carrierNode).getElementsByTagName("type").item(0).getTextContent();
        String id_key = ((Element) carrierNode).getAttribute("id");
        Carrier carrier = new Carrier();
        carrier.setName(((Element) carrierNode).getElementsByTagName("name").item(0).getTextContent());
        carrier.setAddress(((Element) carrierNode).getElementsByTagName("adress").item(0).getTextContent());
        if (type.equalsIgnoreCase("ship")) {
            carrier.setCarrierType(CarrierType.SHIP);
        }
        if ((type.equalsIgnoreCase("plane"))) {
            carrier.setCarrierType(CarrierType.PLANE);
        }
        if ((type.equalsIgnoreCase("car"))) {
            carrier.setCarrierType(CarrierType.CAR);
        }
        if ((type.equalsIgnoreCase("train"))) {
            carrier.setCarrierType(CarrierType.TRAIN);
        }
        return new SimpleEntry<String, Carrier>(id_key, carrier);
    }

    private Map<String, Cargo> parseCargosFromDoc(Document doc) {
        Map<String, Cargo> cargos = new LinkedHashMap<>();
        Node root = doc.getElementsByTagName("root").item(0);
        NodeList allCargos = ((Element) root).getElementsByTagName("Cargo");
        int length = allCargos.getLength();
        for (int i = 0; i < length; i++) {
            Node cargoNode = allCargos.item(i);
            SimpleEntry<String, Cargo> entry = createCargoEntryFromXMLNode(cargoNode);
            if (entry != null) {
                cargos.put(entry.getKey(), entry.getValue());
            }
        }
        return cargos;
    }

    private SimpleEntry<String, Cargo> createCargoEntryFromXMLNode(Node cargoNode) {
        String key_id = ((Element) cargoNode).getAttribute("id");
        String type = ((Element) cargoNode).getAttribute("type");
        int weight = Integer.parseInt(((Element) cargoNode).getElementsByTagName("weight").item(0).getTextContent());
        String name = ((Element) cargoNode).getElementsByTagName("name").item(0).getTextContent();
        Cargo cargoToAdd = null;
        if (type.equalsIgnoreCase("food")) {
            cargoToAdd = new FoodCargo();
            Date date = yieldDateFromString(((Element) cargoNode).getElementsByTagName("expiration_date").item(0).getTextContent());
            int temperatureToStore = Integer.parseInt(((Element) cargoNode).getElementsByTagName("store_temperature").item(0).getTextContent());
            cargoToAdd.setName(name);
            cargoToAdd.setWeight(weight);
            ((FoodCargo) cargoToAdd).setExpirationDate(date);
            ((FoodCargo) cargoToAdd).setStoreTemperature(temperatureToStore);
        }
        if (type.equalsIgnoreCase("clothes")) {
            cargoToAdd = new ClothersCargo();
            String size = (((Element) cargoNode).getElementsByTagName("size").item(0).getTextContent());
            String material = ((Element) cargoNode).getElementsByTagName("material").item(0).getTextContent();
            cargoToAdd.setName(name);
            cargoToAdd.setWeight(weight);
            ((ClothersCargo) cargoToAdd).setSize(size);
            ((ClothersCargo) cargoToAdd).setMaterial(material);
        }
        return new SimpleEntry<String, Cargo>(key_id, cargoToAdd);
    }

    private Date yieldDateFromString(String expiration_date) {
        String[] dateComponents = expiration_date.split("\\.", 3);
        Date dateToReturn = new Date();
        dateToReturn.setDate(Integer.parseInt(dateComponents[0]));
        dateToReturn.setMonth(Integer.parseInt(dateComponents[1]));
        dateToReturn.setYear(Integer.parseInt(dateComponents[2]) - 1900);
        return dateToReturn;
    }

    private Document getDocumentFromFile() throws ParserConfigurationException, IOException, SAXException {
        System.out.println(file.exists());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        return doc;
    }


    private void persistTransportations(List<Transportation> transportationList) {
        for (Transportation transportation : transportationList) {
            ServiceHolder.getInstance().getTransportationService().save(transportation);
        }
    }

    private void persistCarriers(Collection<Carrier> values) {
        for (Carrier carrier : values) {
            ServiceHolder.getInstance().getCarrierService().save(carrier);
        }
    }

    private void persistCargos(Collection<Cargo> values) {
        for (Cargo cargo : values) {
            ServiceHolder.getInstance().getCargoService().save(cargo);
        }
    }
}
