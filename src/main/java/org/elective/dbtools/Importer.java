package org.elective.dbtools;

import org.apache.log4j.Logger;
import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Importer<T> {
    private static final Logger log = Logger.getLogger(Importer.class);
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
    public List<T> load(Class<T> clazz, String condition) throws ORMException{
        List<Field> fields= Tools.getAllMatchingFields(clazz);
        if (fields.isEmpty())
            throw new ORMException("There are no fields marked with annotations TableField(...)");
        String query = createSQLQuery(clazz, fields, condition);

        List<T> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            if (rs.getMetaData().getColumnCount() != fields.size())
                throw new ORMException("Wrong fields count");
            while (rs.next()) {
                T obj = createNewEntity(clazz);
                Tools.setObjectFieldsFromResultSet(obj, fields, rs);
                result.add(obj);
            }

        } catch (SQLException exception) {
            throw new ORMException();
        }
        return result;
    }

    public Optional<T> loadOne(Class<T> clazz, String condition) throws ORMException {
        List<Field> fields= Tools.getAllMatchingFields(clazz);
        if (fields.isEmpty())
            throw new ORMException("There are no fields marked with annotations TableField(...)");
        String query = createSQLQuery(clazz, fields, condition);
        log.info("Query: '" + query + "'");
        T obj;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            if (rs.getMetaData().getColumnCount() != fields.size())
                throw new ORMException("Wrong fields count");
            if (rs.next()) {
                obj = createNewEntity(clazz);
                Tools.setObjectFieldsFromResultSet(obj, fields, rs);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {
            throw new ORMException(exception.getMessage());
        }
        return Optional.of(obj);
    }

    public int getTableCount(Class<T> clazz) throws ORMException {
        if (Tools.getAllMatchingFields(clazz).isEmpty())
            throw new ORMException("There are no fields marked with annotations TableField(...)");
        String query = "SELECT COUNT(*) FROM " + Tools.getSQLNameFromClass(clazz) + ";";
        int count;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
             count = rs.next()? rs.getInt(1): 0;
        } catch (SQLException exception) {
            throw new ORMException(exception.getMessage());
        }
        return count;
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

    private T createNewEntity(Class<T> clazz) throws ORMException{
        T obj;
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
