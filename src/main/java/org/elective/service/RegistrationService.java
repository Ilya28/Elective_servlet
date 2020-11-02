package org.elective.service;

import org.apache.log4j.Logger;
import org.elective.dbtools.ConnectionPoolHolder;
import org.elective.dbtools.Exporter;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.Registration;
import org.elective.entities.User;
import org.elective.service.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegistrationService implements Service{
    private static final Logger log = Logger.getLogger(RegistrationService.class);
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public List<Registration> getRegistrationsByUserEmail(String email, Page page) {
        Importer<Registration> importer = null;
        Importer<User> userImporter = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            userImporter = new Importer<>(con);
            Optional<User> user = userImporter.loadOne(User.class, "WHERE email='"+email+"'");
            if (!user.isPresent()) {
                log.warn("User {" + email + "} not fond");
                return Collections.emptyList();
            }
            return importer.load(Registration.class, "WHERE user=" + user.get().getId() +
                    " LIMIT " + (page.getPage()-1)*4 + ", " + page.getPage()*4);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM exception", e);
            return Collections.emptyList();
        }
    }

    public void addRegistration(Registration registration) {
        Exporter<Registration> exporter = null;
        try (Connection con = dataSource.getConnection()){
            exporter = new Exporter<>(con);
            exporter.save(registration);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
        }
    }

    public void addRegistration(String username, Long courseId) {
        UserService userService = new UserService();
        Optional<User> user = userService.getUserByEmail(username);
        if (!user.isPresent()) {
            log.error("User not fond {"+username+"}");
            return;
        }
        Registration registration = Registration.toBuilder()
                .course(courseId)
                .user(user.get().getId())
                .grade(0)
                .progress(0)
                .build();
        addRegistration(registration);
    }

    public void deleteByUsernameAndCourseId(String username, Long courseId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(
                            "DELETE registrations FROM registrations INNER JOIN usr " +
                             "ON registrations.user=usr.id " +
                             "WHERE usr.email=? AND registrations.course=?;")) {
             statement.setString(1, username);
             statement.setLong(2, courseId);
             log.info("Prepared statement: " + statement.toString());
             int updated = statement.executeUpdate();
             if (updated != 1)
                 log.warn("Deleting error. More or less than one ("+updated+") updated rows");
        } catch (SQLException e) {
            log.error("deleteByUsernameAndCourseId: Cant execute a query");
        }
    }



    public boolean isRegistered(String username, Long courseId) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "SELECT * FROM registrations INNER JOIN usr " +
                     "ON registrations.user=usr.id " +
                     "WHERE usr.email=? AND registrations.course=?;")) {
            statement.setString(1, username);
            statement.setLong(2, courseId);
            return  statement.executeQuery().next();
        } catch (SQLException e) {
            log.error("isRegistered: Cant execute a query");
            return false;
        }
    }

    public Page pagination(HttpServletRequest request) {
        Importer<Registration> importer = null;
        Page page = new Page();
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            page.setPage(request);
            page.setCount(importer.getTableCount(Registration.class)/4 + 1);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
        }
        return page;
    }
}
