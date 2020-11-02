package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.service.Security;
import org.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class LogOutCommand implements Command {
    private static final String LOGIN_PAGE = "login";
    private static final Logger log = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HashSet<String> loggedUsers = (HashSet<String>) request
                .getSession()
                .getServletContext()
                .getAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER);
        String username = (String) request
                .getSession()
                .getAttribute(Security.ATTRIBUTE_USERNAME_HOLDER);
        log.info("Remove user from  context {" + username + "}");
        log.info("logged users: " + loggedUsers.toString());
        loggedUsers.remove(username);
        log.info("logged users: " + loggedUsers.toString());
        request.getSession().getServletContext().setAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
        Security.setUserRole(request, Security.UNKNOWN, "");
        return "redirect:/" + LOGIN_PAGE;
    }
}
