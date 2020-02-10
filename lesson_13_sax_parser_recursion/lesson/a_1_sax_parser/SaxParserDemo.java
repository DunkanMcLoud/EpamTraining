package ru.epam.javacore.lesson_13_sax_parser_recursion.lesson.a_1_sax_parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class SaxParserDemo {
    public static void main(String[] args) throws Exception, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PersonHandler personHandler = new PersonHandler();
        File file = new File("C:\\Users\\student\\Desktop\\ttt\\epamjavacore\\resources\\ru\\epam\\javacore\\lesson_13\\persons.xml");
        saxParser.parse(file, personHandler);

        List<Person> persons = personHandler.getPersons();
        System.out.println();
    }
}

