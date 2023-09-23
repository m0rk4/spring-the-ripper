package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marker annotation.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Profiling {
}
