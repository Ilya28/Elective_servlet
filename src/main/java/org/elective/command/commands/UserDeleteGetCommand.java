package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteGetCommand implements Command {
    private static final Logger log = Logger.getLogger(UserDeleteGetCommand.class);
    private final UserService userService;

    public UserDeleteGetCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id;
        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            log.error("Wrong id param");
            return "redirect:/users";
        }
        userService.deleteById(id);
        return "redirect:/users";
    }
}