package org.elective.service;

import org.apache.log4j.Logger;
import org.elective.dbtools.ConnectionPoolHolder;
import org.elective.dbtools.Exporter;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
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

public class UserService implements Service {
    private static final Logger log = Logger.getLogger(UserService.class);
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

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

    public Optional<User> getUserById(Long id) {
        Importer<User> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.loadOne(User.class, "WHERE id=" + id);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection: " + e.getMessage());
        } catch (ORMException e) {
            log.error("ORM exception", e);
            return Optional.empty();
        }
    }

    public List<User> getAllUsers(Page page) {
        Importer<User> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.load(User.class,
                    "LIMIT " + (page.getPage()-1)*4 + ", " + page.getPage()*4);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
            return Collections.emptyList();
        }
    }

    public Page pagination(HttpServletRequest request) {
        Importer<User> importer = null;
        Page page = new Page();
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            page.calcPageCountFromObjectsCount(importer.getTableCount(User.class));
            page.setPage(request);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
        }
        return page;
    }

    public boolean deleteById(Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM usr WHERE id=?;")) {
            statement.setLong(1, id);
            log.info("Generated statement: " + statement.toString());
            int updated = statement.executeUpdate();
            if (updated != 1) {
                log.error("0 or mor then one updated ("+updated+")");
                return false;
            }
        } catch (SQLException e) {
            log.error("Cant delete this user");
            return false;
        }
        return true;
    }

    public boolean save(User user) {
        Exporter<User> exporter;
        try (Connection con = dataSource.getConnection()){
            exporter = new Exporter<>(con);
            exporter.save(user);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.warn("ORM error or user with this email already exist", e);
            return false;
        }
        return true;
    }
}
