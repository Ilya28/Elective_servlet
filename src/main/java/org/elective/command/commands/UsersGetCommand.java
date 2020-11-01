package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersGetCommand implements Command {
    private static final String PAGE_NAME = "users";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        // TODO: condition (for pagination)
        Importer<User> importer = new Importer<>(null);
        List<User> users = importer.load(User.class, null);
        request.setAttribute("users", users);
        return PAGE_NAME;
    }
}