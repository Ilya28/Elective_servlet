package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.command.CommandResolver;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;
import org.elective.service.Security;
import org.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginPostCommand implements Command {
    private static final String HOME_PAGE = "home";
    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_ERROR = "redirect:/" + LOGIN_PAGE + "?error";

    private static final Logger log = Logger.getLogger(LoginPostCommand.class);

    private final UserService userService;
    public LoginPostCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        String username = request.getParameter("email");
        String password = request.getParameter("pass");
        log.info("Checking auth: {" + username + ", " + password + "}");
        if (username == null || password == null || username.isEmpty() || password.isEmpty())
            return LOGIN_ERROR;
        Optional<User> expectedUser = userService.getUserByEmail(username);
        if (!expectedUser.isPresent()) {
            log.info("User not fond");
            return LOGIN_ERROR;
        }
        if (!password.equals(expectedUser.get().getPassword())) {
            log.info("Password incorrect");
            return LOGIN_ERROR;
        }
        if (Security.checkUserIsLogged(request, username)) {
            log.info("User already exist in system");
            return LOGIN_ERROR;
        }
        log.info("Successful - user role: " + expectedUser.get().getRole());
        Security.setUserRole(request, Security.valueOf(expectedUser.get().getRole()), username);

        return "redirect:/" + HOME_PAGE;
    }
}
