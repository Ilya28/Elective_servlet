package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotExistCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "404, command (page) does not fond");
        return WebPaths.nameToPath("errorPage");
    }
}
