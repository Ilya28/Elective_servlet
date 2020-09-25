package org.elective.dbtools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface TableField {
    int TF_REGULAR = 0;
    int TF_GENERATED = 1;
    String name() default "";
    int type() default TF_REGULAR;
}
