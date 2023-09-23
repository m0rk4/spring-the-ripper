package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface DeprecatedClass {

    Class<?> newImpl();
}
