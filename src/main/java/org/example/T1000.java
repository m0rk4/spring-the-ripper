package org.example;

// implements Quoter - workaround to leverage jdk-based proxies
public class T1000 extends TerminatorQuoter implements Quoter {

    @Override
    public void sayQuote() {
        System.out.println("I'm liquid");
    }
}
