package ru.epam.javacore.lesson_13_sax_parser_recursion.lesson.a_1_sax_parser;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends DefaultHandler {
    private List<Person> persons = new ArrayList<>();
    private List<Child> children;
    private Child child;
    private Person curPerson;

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
        stringBuilder.setLength(0);


        switch (qName) {
            case "person": {
                curPerson = new Person();
                break;
            }
            case "children": {
                children = new ArrayList<>();
                break;
            }

            case "child": {
                child = new Child();
                String schoolBoy = attributes.getValue("schoolBoy");
                child.setSchoolBoy("y".equals(schoolBoy));
                break;
            }

            case "lastName": {
                String prev = attributes.getValue("prev");
                curPerson.setPrevLastName(prev);
                break;
            }

        }
    }

    @Override
    public void endElement(String s, String s1, String qName) {
        String data = stringBuilder.toString();
        switch (qName) {
            case "name": {
                if (child == null) {
                    curPerson.setName(data);
                } else {
                    child.setName(data);
                }
                break;
            }

            case "lastName": {
                curPerson.setLastName(data);
                break;
            }

            case "hobby": {
                child.setHobby(data);
                break;
            }

            case "child": {
                children.add(child);
                child = null;
                break;
            }

            case "children": {
                curPerson.setChildren(children);
                break;
            }

            case "person": {
                persons.add(curPerson);
                break;
            }
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        String data = new String(chars, start, length);
        stringBuilder.append(data);
    }

    public List<Person> getPersons() {
        return persons;
    }
}
