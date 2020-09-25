package org.elective.dbtools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Table {
    int T_REGULAR = 0;
    int T_COMBINED = 1;
    String name() default "";
    int type() default T_REGULAR;
    boolean fieldsAutoNaming() default false;
}
