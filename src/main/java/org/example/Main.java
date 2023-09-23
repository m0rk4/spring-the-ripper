package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        // never get bean by its class (class can be proxied, etc.)
        // prototype beans doesn't invoke destroy method
        //        Quoter quoter = context.getBean(Quoter.class);
        //        quoter.sayQuote();
    }
}
