package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.entities.Course;
import org.elective.service.CourseService;
import org.elective.service.pagination.Page;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CoursesGetCommand implements Command {
    private static final String COURSES_PAGE = "courses";
    private static final Logger log = Logger.getLogger(CoursesGetCommand.class);
    private final CourseService courseService;

    public CoursesGetCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Page page = courseService.pagination(request);
        List<Course> courses = courseService.getCoursesPage(page);
        request.setAttribute("pages", page.getCount());
        request.setAttribute("courses", courses);
        return WebPaths.nameToPath(COURSES_PAGE);
    }
}