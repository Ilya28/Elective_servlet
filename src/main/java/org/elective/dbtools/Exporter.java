package org.elective.dbtools;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;
import org.elective.dbtools.exceptions.ORMException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exporter {
    private Connection connection;

    public Exporter(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method saves the annotated class fields to the database.
     * @param obj Object to save to database
     * @throws ORMException When any error occurs
     */
    public void save(Object obj) throws ORMException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class))
            throw new ORMException("The class must marked with annotation `Table(...)`");
        List<Field> fields;
        Stream<Field> stream = Arrays.stream(clazz.getDeclaredFields());
        if (clazz.getAnnotation(Table.class).fieldsAutoNaming())
            fields = stream
                    .filter(f->
                            !f.isAnnotationPresent(TableField.class) ||
                            f.getAnnotation(TableField.class).type() == TableField.TF_REGULAR)
                    .collect(Collectors.toList());
        else
            fields = stream
                .filter(f->f.isAnnotationPresent(TableField.class))
                .filter(f->f.getAnnotation(TableField.class).type() == TableField.TF_REGULAR)
                .collect(Collectors.toList());
        if (fields.isEmpty())
            throw new ORMException("There are no fields marked with annotations TableField(...)");

        String query = createSQLQuery(obj, fields);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            throw new ORMException("SQL Error: " + exception.getMessage());
        }
    }

    private String createSQLQuery(Object obj, List<Field> fields) throws ORMException{
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(Tools.getSQLNameFromClass(clazz)).append('(');

        boolean first = true;
        for (Field field: fields) {
            if (!first)
                query.append(',');
            else
                first = false;
            query.append(Tools.getSQLNameFromField(field));
        }
        query.append(") VALUES(");
        first = true;
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                if (!first)
                    query.append(',');
                else
                    first = false;

                boolean useQuotes =
                        field.getType() == byte[].class ||
                        field.getType() == String.class;

                if (useQuotes) query.append('"');
                if (field.getType() == byte[].class) {
                    // For BINARY fields
                    for (byte b : (byte[]) field.get(obj))
                        query.append((char) b);
                } else {
                    query.append(field.get(obj));
                }
                if (useQuotes) query.append('"');
            } catch (IllegalAccessException e) {
                throw new ORMException("Reflection: Illegal access to field `" + field.getName() + "`");
            }
        }
        query.append(");");
        return query.toString();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
