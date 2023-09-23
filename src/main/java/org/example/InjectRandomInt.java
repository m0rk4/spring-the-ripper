package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// SOURCE  - only visible in sources, discarded in bytecode (@Override)
// CLASS   - will be bytecode, but not available int runtime (AT-transformation, bytecode instrumentation)
// RUNTIME - available in runtime
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {

    int min();

    int max();
}
