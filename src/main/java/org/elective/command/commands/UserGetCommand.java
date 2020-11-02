package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;
import org.elective.service.RegistrationService;
import org.elective.service.UserService;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserGetCommand implements Command {
    private static final String PAGE_NAME = "user";
    private static final Logger log = Logger.getLogger(UserGetCommand.class);

    UserService userService;

    public UserGetCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        Long id;
        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            log.error("Wrong id param");
            return "redirect:/users";
        }
        Optional<User> user = userService.getUserById(id);
        if (!user.isPresent()) {
            log.error("User not fond");
            return "redirect:/users";
        }
        request.setAttribute("user", user.get());
        return WebPaths.nameToPath(PAGE_NAME);
    }
}