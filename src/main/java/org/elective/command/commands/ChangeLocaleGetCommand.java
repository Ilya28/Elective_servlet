package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleGetCommand implements Command {
    private static final String PAGE_NAME = "users";
    private static final Logger log = Logger.getLogger(ChangeLocaleGetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        log.info("Locale changed");
        return "redirect:/home";
    }
}
