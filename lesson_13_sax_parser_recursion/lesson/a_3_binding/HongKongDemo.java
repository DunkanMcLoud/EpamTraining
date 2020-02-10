package ru.epam.javacore.lesson_13_sax_parser_recursion.lesson.a_3_binding;

public class HongKongDemo {

    private static class Parent {
        private void test1() {
            System.out.println("parent test");
        }

        public void test2() {
            System.out.println("parent test2");
        }

        public static void test3(){
            System.out.println("parent test3");
        }
    }

    private static class Child extends Parent {
        private void test1(){
            System.out.println("Child test");
        }

        public void test2(){
            System.out.println("Child test2");
        }

        public static void test3(){
            System.out.println("Child 3");
        }
    }

    public static void main(String[] args) {
        Parent parent = new Child();
        HongKongDemo  hongKongDemo = new HongKongDemo();
        hongKongDemo.method(parent);
    } 
    public void method(Child child){
        System.out.println("child");
        child.test1();
        child.test2();
		child.test3();
    }
    public void method(Parent parent){
        System.out.println("Parent");
		parent.test1();
        parent.test2();
		parent.test3();
    }
}
