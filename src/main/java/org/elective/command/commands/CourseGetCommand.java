package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.entities.Course;
import org.elective.service.CourseService;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CourseGetCommand implements Command {
    private final CourseService courseService;

    public CourseGetCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            return "redirect:/courses?course_not_fond";
        }
        Optional<Course> course = courseService.getCourseById((long) id);
        if (!course.isPresent())
            return "redirect:/courses?course_not_fond";
        request.setAttribute("course", course.get());
        return WebPaths.nameToPath("course");
    }
}