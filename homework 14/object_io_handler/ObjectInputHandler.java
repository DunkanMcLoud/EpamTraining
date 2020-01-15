package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.object_io_handler;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.transportation.domain.Transportation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectInputHandler {
    private File fileToImportData;

    public ObjectInputHandler(File file) {
        fileToImportData = file;
    }

    public List<Cargo> importCargos() throws IOException {
        List<Cargo> answer = new ArrayList<>();
        try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileToImportData))) {
            while (true) {
                try {
                    Cargo cargotoAdd = (Cargo) objectInput.readObject();
                    answer.add(cargotoAdd);
                } catch (Exception e) {
                    System.out.println("Parsing is finished");
                    return answer;
                }}
            }
}

        public List<Transportation> importTransportations () throws IOException, ClassNotFoundException {
            List<Transportation> answer = new ArrayList<>();
            try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileToImportData))) {
                while (objectInput.available() > 0) {
                    answer.add((Transportation) objectInput.readObject());
                }
            }
            return answer;
        }

        public List<Carrier> importCarrriers () throws IOException, ClassNotFoundException {
            List<Carrier> answer = new ArrayList<>();
            try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileToImportData))) {
                while (objectInput.available() > 0) {
                    answer.add((Carrier) objectInput.readObject());
                }
            }
            return answer;
        }
    }
