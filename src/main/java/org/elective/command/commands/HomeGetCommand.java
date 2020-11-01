package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.entities.Registration;
import org.elective.service.RegistrationService;
import org.elective.service.Security;
import org.elective.service.WebPaths;
import org.elective.service.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class HomeGetCommand implements Command {
    private static final Logger log = Logger.getLogger(HomeGetCommand.class);
    private static final String USER_HOME = "user_home";
    private static final String TEACHER_HOME = "teacher_home";

    private final RegistrationService registrationService;

    public HomeGetCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getServletContext().getAttribute(Security.ATTRIBUTE_USERNAME_HOLDER);
        Page page = registrationService.pagination(request);
        List<Registration> registrations = Collections.emptyList();
        if (username != null)
            registrations = registrationService.getRegistrationsByUserEmail(username, page);
        else
            log.error("Null username");
        request.setAttribute("registrations", registrations);
        Security role = Security.getUserRole(request);
        return WebPaths.nameToPath((role == Security.USER)? USER_HOME: TEACHER_HOME);
    }
}
