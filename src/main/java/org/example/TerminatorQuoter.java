package org.example;

import javax.annotation.PostConstruct;

@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println("1: Constructor invoked");
    }

    // tell spring to include post processor which knows about this annotation (see context.xml)
    // Don't call heavy/async init logic. Proxies aren't ready, creation lock is acquired.
    @PostConstruct
    public void init() {
        System.out.println("3: Init method: " + repeat);
    }

    public void setMessage(String message) {
        System.out.println("2: Setter invoked with: " + message);
        this.message = message;
    }

    public void setRepeat(int repeat) {
        System.out.println("2: Setter invoked with: " + repeat);
        this.repeat = repeat;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("4: Triggered by context refreshed event or by direct method invocations.");
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message: " + message);
        }
    }
}
