package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.entities.Course;
import org.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class CourseAddPostCommand implements Command {
    private final CourseService courseService;

    public CourseAddPostCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Course course = Course.toBuilder()
                .nameEN(request.getParameter("name_en"))
                .nameUA(request.getParameter("name_ua"))
                .subject(Long.valueOf(request.getParameter("subject")))
                .descriptionEN(request.getParameter("description_en"))
                .descriptionUA(request.getParameter("description_ua"))
                .dateStart(LocalDate.parse(request.getParameter("date_start")))
                .dateEnd(LocalDate.parse(request.getParameter("date_end")))
                .seats(Integer.parseInt(request.getParameter("seats")))
                .signedUp(0)
                .build();
        courseService.addCourse(course);
        return "redirect:/courses";
    }
}