package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marker annotation. See {@link PostProxyInvokerContextListener}.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PostProxy {
}
