package org.elective.command.commands;

import org.elective.command.Command;
import org.elective.dbtools.Importer;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserGetCommand implements Command {
    private static final String PAGE_NAME = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        // TODO: condition (for pagination)
        Importer<User> importer = new Importer<>(null);
        Optional<User> user = importer.loadOne(User.class, null);
        request.setAttribute("user", user);
        return PAGE_NAME;
    }
}