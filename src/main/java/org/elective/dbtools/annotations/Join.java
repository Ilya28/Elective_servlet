package org.elective.dbtools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Join {
    int JOIN_INNER = 0;
    int JOIN_LEFT = 1;
    int JOIN_RIGHT = 2;
    int JOIN_FULL = 3;
    String name();
    int type();
}
