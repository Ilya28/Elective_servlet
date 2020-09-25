package org.elective.dbtools;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;
import org.elective.dbtools.exceptions.ORMException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Importer {
    private Connection connection;

    public Importer(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method loads strings from the database and converts them to a <b>list of objects</b>.
     * @param clazz Class to be loaded.
     * @param condition SQL query condition (ex: WHERE ...) to filter objects in the table
     * @return All objects from JDBC ResultSet
     * @throws ORMException When any error occurs
     */
    public List<Object> Load(Class<?> clazz, String condition) throws ORMException{
        if (!clazz.isAnnotationPresent(Table.class))
            throw new ORMException("The class must marked with annotation `Table(...)`");
        List<Field> fields;
        if (clazz.getAnnotation(Table.class).fieldsAutoNaming())
            fields = Arrays.asList(clazz.getDeclaredFields());
        else
            fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f->f.isAnnotationPresent(TableField.class))
                .collect(Collectors.toList());
        if (fields.isEmpty())
            throw new ORMException("There are no fields marked with annotations TableField(...)");
        String query = createSQLQuery(clazz, fields, condition);

        List<Object> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query.toString())) {
            if (rs.getMetaData().getColumnCount() != fields.size())
                throw new ORMException("Wrong fields count");
            while (rs.next()) {
                Object obj = createNewEntity(clazz);
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = Tools.getSQLNameFromField(field);
                    try {
                        field.set(obj, rs.getObject(fieldName));
                    } catch (IllegalAccessException exception) {
                        throw new ORMException("Reflection: Illegal access to field `" + field.getName() + "`");
                    } catch (SQLException exception) {
                        throw new ORMException("Unknown field `" + fieldName + "`");
                    }
                }
                result.add(obj);
            }

        } catch (SQLException exception) {
            throw new ORMException();
        }
        return result;
    }

    private String createSQLQuery(Class<?> clazz, List<Field> fields, String condition) {
        StringBuilder query = new StringBuilder("SELECT ");
        boolean first = true;
        for (Field field: fields) {
            if (!first)
                query.append(',');
            else
                first = false;
            query.append(Tools.getSQLNameFromField(field));
        }
        query.append(" from ");
        query.append(Tools.getSQLNameFromClass(clazz));
        if (condition != null)
            query.append(' ').append(condition);
        query.append(';');
        return query.toString();
    }

    private Object createNewEntity(Class<?> clazz) throws ORMException{
        Object obj;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ORMException("Failed to instantiate class: " + e.getMessage());
        }
        return obj;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
