package org.elective.dbtools;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;
import java.lang.reflect.Field;

public class Tools {
    public static String getSQLNameFromField(Field field) {
        if (field.isAnnotationPresent(TableField.class) &&
                !"".equals(field.getAnnotation(TableField.class).name()))
            return field.getAnnotation(TableField.class).name();
        else
            return NamesConverter.toSQLName(field.getName());
    }

    public static String getSQLNameFromClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class) &&
                !"".equals(clazz.getAnnotation(Table.class).name()))
            return clazz.getAnnotation(Table.class).name();
        else
            return NamesConverter.toSQLName(clazz.getName());
    }
}
