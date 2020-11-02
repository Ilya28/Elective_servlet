package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.entities.User;
import org.elective.service.Security;
import org.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPostCommand implements Command {
    private static final Logger log = Logger.getLogger(RegisterPostCommand.class);

    private final UserService userService;
    public RegisterPostCommand(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("email");
        String password = request.getParameter("pass");
        String name = request.getParameter("name");
        if (username == null || password == null || name == null ||
                username.isEmpty() || password.isEmpty() || name.isEmpty())
            return "redirect:/register";
        User user = User.toBuilder()
                .password(password)
                .email(username)
                .name(name)
                .blocked(false)
                .language("en")
                .role(Security.USER.name())
                .build();
        log.info("Add new user {" + username + ", " + name + ", " + password);
        return userService.save(user)? "redirect:/login": "redirect:/register";
    }
}