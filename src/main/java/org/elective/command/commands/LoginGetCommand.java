package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.WebPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGetCommand implements Command {
    private static final String PAGE_NAME = "login";

    private final LocalizedTextSupplier l18nText = LocalizedTextSupplier.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        String locale = "en"; // todo: localization
        if (request.getParameter("error") != null)
            request.setAttribute("message", l18nText.getLocalizedText("login.error", locale));
        return WebPaths.nameToPath(PAGE_NAME);
    }
}
