package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.service.RegistrationService;
import org.elective.service.Security;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Security role = Security.getUserRole(request);
        return WebPaths.nameToPath((role == Security.USER)? USER_HOME: TEACHER_HOME);
    }
}
