package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.service.RegistrationService;
import org.elective.service.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseRegisterCommand implements Command {
    private static final Logger log = Logger.getLogger(CourseRegisterCommand.class);
    private final RegistrationService registrationService;

    public CourseRegisterCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute(Security.ATTRIBUTE_USERNAME_HOLDER);
        if (username == null) {
            log.error("Null username");
            return "redirect:/courses";
        }
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            if (!registrationService.isRegistered(username, id))
                registrationService.addRegistration(username, id);
            else
                log.info("This user already registered for this course");

        } catch (NumberFormatException e) {
            log.error("Missing course id parameter");
        }
        return "redirect:/courses";
    }
}
