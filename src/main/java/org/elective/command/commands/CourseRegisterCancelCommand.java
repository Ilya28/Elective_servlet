package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.service.RegistrationService;
import org.elective.service.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseRegisterCancelCommand  implements Command {
    private static final Logger log = Logger.getLogger(CourseRegisterCancelCommand.class);
    private final RegistrationService registrationService;

    public CourseRegisterCancelCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getServletContext().getAttribute(Security.ATTRIBUTE_USERNAME_HOLDER);
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            if (username != null)
                registrationService.deleteByUsernameAndCourseId(username, id);
            else
                log.error("Null username");
        } catch (NumberFormatException e) {
            log.error("Missing registration id parameter");
        }
        return "redirect:/home";
    }
}
