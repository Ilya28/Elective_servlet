package org.elective.service;

import org.apache.log4j.Logger;
import org.elective.controller.Controller;
import org.elective.dbtools.ConnectionPoolHolder;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserService implements Service {
    private static final Logger log = Logger.getLogger(UserService.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public Optional<User> getUserByEmail(String email) {
        Importer<User> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.loadOne(User.class, "WHERE email='" + email + "'");
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection: " + e.getMessage());
        } catch (ORMException e) {
            log.error("ORM exception", e);
            return Optional.empty();
        }
    }
}
