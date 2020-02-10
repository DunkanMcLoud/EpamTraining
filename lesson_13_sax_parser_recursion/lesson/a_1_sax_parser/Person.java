package ru.epam.javacore.lesson_13_sax_parser_recursion.lesson.a_1_sax_parser;

import java.util.List;

public class Person {
    private String name;
    private String lastName;
    private String prevLastName;
    private List<Child> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrevLastName() {
        return prevLastName;
    }

    public void setPrevLastName(String prevLastName) {
        this.prevLastName = prevLastName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
