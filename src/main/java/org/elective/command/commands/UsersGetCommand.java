package org.elective.command.commands;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.entities.User;
import org.elective.service.UserService;
import org.elective.service.WebPaths;
import org.elective.service.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersGetCommand implements Command {
    private static final String PAGE_NAME = "users";
    private static final Logger log = Logger.getLogger(UsersGetCommand.class);

    private final UserService userService;

    public UsersGetCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException {
        Page page = userService.pagination(request);
        log.info("Page = " + page.getPage());
        List<User> users = userService.getAllUsers(page);
        request.setAttribute("users", users);
        request.setAttribute("page", page.getPage());
        request.setAttribute("pages", page.getCount());
        return WebPaths.nameToPath(PAGE_NAME);
    }
}