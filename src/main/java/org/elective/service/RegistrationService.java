package org.elective.service;

import org.apache.log4j.Logger;
import org.elective.dbtools.ConnectionPoolHolder;
import org.elective.dbtools.Exporter;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.Registration;
import org.elective.entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegistrationService implements Service{
    private static final Logger log = Logger.getLogger(RegistrationService.class);
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public List<Registration> getRegistrationsByUserId(Integer id, Integer page) {
        Importer<Registration> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.load(Registration.class, "WHERE id=" + id + " LIMIT " + (page-1)*4 + ", " + page*4);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
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



    public boolean isRegistered(String username, Integer courseId) {
        /*try (Connection con = dataSource.getConnection();
             //  todo: query
             //PreparedStatement statement = con.prepareStatement("SELECT * FROM courses INNER JOIN WHERE id=?;")) {
            //statement.setString(1, id.toString());
            //return  statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }*/
        return false;
    }
}
