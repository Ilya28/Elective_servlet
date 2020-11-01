package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseDeleteGetCommand implements Command {
    private final CourseService courseService;

    public CourseDeleteGetCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        courseService.deleteCourseById(Integer.parseInt(request.getParameter("id")));
        return "redirect:/courses";
    }
}