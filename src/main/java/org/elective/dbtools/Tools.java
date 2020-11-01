package org.elective.dbtools;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;
import org.elective.dbtools.exceptions.ORMException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Field> getAllMatchingFields(Class<?> clazz) throws ORMException{
        if (!clazz.isAnnotationPresent(Table.class))
            throw new ORMException("The class must marked with annotation `Table(...)`");
        List<Field> fields;
        if (clazz.getAnnotation(Table.class).fieldsAutoNaming())
            fields = Arrays.asList(clazz.getDeclaredFields());
        else
            fields = Arrays.stream(clazz.getDeclaredFields())
                    .filter(f->f.isAnnotationPresent(TableField.class))
                    .collect(Collectors.toList());
        return fields;
    }

    public static void setObjectFieldsFromResultSet(Object obj, List<Field> fields, ResultSet rs)
            throws ORMException {
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = Tools.getSQLNameFromField(field);
            try {
                Object extracted = rs.getObject(fieldName);
                if (extracted instanceof java.sql.Date)
                    extracted = ((java.sql.Date)extracted).toLocalDate();
                field.set(obj, extracted);
            } catch (IllegalAccessException exception) {
                throw new ORMException("Reflection: Illegal access to field `" + field.getName() + "`");
            } catch (SQLException exception) {
                throw new ORMException("Unknown field `" + fieldName + "`");
            }
        }
    }
}
