package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return WebPaths.nameToPath("register");
    }
}