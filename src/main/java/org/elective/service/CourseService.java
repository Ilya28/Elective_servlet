package org.elective.service;

import org.apache.log4j.Logger;
import org.elective.dbtools.ConnectionPoolHolder;
import org.elective.dbtools.Exporter;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.Course;
import org.elective.service.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseService implements Service {
    private static final Logger log = Logger.getLogger(CourseService.class);
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public Optional<Course> getCourseById(Long id) {
        Importer<Course> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.loadOne(Course.class,"WHERE id=" + id);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
            return Optional.empty();
        }
    }

    public void addCourse(Course course) {
        Exporter<Course> exporter = null;
        try (Connection con = dataSource.getConnection()){
            exporter = new Exporter<>(con);
            exporter.save(course);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
        }
    }

    public boolean deleteCourseById(Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM courses WHERE id=?;")) {
            statement.setString(1, id.toString());
            return  statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    public Page pagination(HttpServletRequest request) {
        Importer<Course> importer = null;
        Page page = new Page();
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            page.setPage(request);
            page.setCount(importer.getTableCount(Course.class)/4 + 1);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
        }
        return page;
    }

    public List<Course> getCoursesPage(Page page) {
        Importer<Course> importer = null;
        try (Connection con = dataSource.getConnection()){
            importer = new Importer<>(con);
            return importer.load(Course.class,
                    "LIMIT " + (page.getPage()-1)*4 + ", " + page.getPage()*4);
        } catch (SQLException e) {
            throw new IllegalStateException("Can't get connection");
        } catch (ORMException e) {
            log.error("ORM error", e);
            return Collections.emptyList();
        }
    }

}
