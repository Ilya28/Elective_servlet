package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.service.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    private static final String LOGIN_PAGE = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // todo: delete current user from context and session
        Security.setUserRole(request, Security.UNKNOWN, "");
        return "redirect:/" + LOGIN_PAGE;
    }
}
