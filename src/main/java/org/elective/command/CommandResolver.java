package org.elective.command;

import org.apache.log4j.Logger;
import org.elective.command.commands.*;
import org.elective.command.tools.CommandKey;
import org.elective.command.tools.CommandsMap;
import org.elective.command.tools.RequestMethod;
import org.elective.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static org.elective.command.tools.RequestMethod.*;

public class CommandResolver {
    private static final String COMMAND_DOES_NOT_EXIST = "Not exist";

    private static final Logger log = Logger.getLogger(CommandResolver.class);

    private static final CommandsMap commands = new CommandsMap();
    static {
        commands.addMapping(
                COMMAND_DOES_NOT_EXIST, METHOD_ANY,
                new NotExistCommand(),
                Security.UNKNOWN, Security.USER, Security.TEACHER, Security.ADMIN
        );
        // ###### LOGIN ######
        commands.addMapping(
                "login", METHOD_GET,
                new LoginGetCommand(),
                Security.UNKNOWN
        );
        commands.addMapping(
                "login", METHOD_POST,
                new LoginPostCommand(new UserService()),
                Security.UNKNOWN
        );
        // ###### LOGOUT ######
        commands.addMapping(
                "logout", METHOD_GET,
                new LogOutCommand(),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        // ###### REGISTER ######
        commands.addMapping(
                "register", METHOD_GET,
                new RegisterGetCommand(),
                Security.UNKNOWN
        );
        commands.addMapping(
                "register", METHOD_POST,
                new RegisterPostCommand(new UserService()),
                Security.UNKNOWN
        );
        // ###### HOME ######
        commands.addMapping(
                "home", METHOD_GET,
                new HomeGetCommand(new RegistrationService()),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        // ###### USER ######
        commands.addMapping(
                "user", METHOD_GET,
                new UserGetCommand(new UserService()),
                Security.ADMIN, Security.TEACHER
        );
        commands.addMapping(
                "user/delete", METHOD_GET,
                new UserDeleteGetCommand(new UserService()),
                Security.ADMIN
        );
        // ###### USERS ######
        commands.addMapping(
                "users", METHOD_GET,
                new UsersGetCommand(new UserService()),
                Security.TEACHER, Security.ADMIN
        );
        // ###### COURSES ######
        commands.addMapping(
                "courses", METHOD_GET,
                new CoursesGetCommand(new CourseService()),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        // ###### COURSE ######
        commands.addMapping(
                "course", METHOD_GET,
                new CourseGetCommand(new CourseService()),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        commands.addMapping(
                "course/add", METHOD_GET,
                new CourseAddGetCommand(),
                Security.TEACHER, Security.ADMIN
        );
        commands.addMapping(
                "course/add", METHOD_POST,
                new CourseAddPostCommand(new CourseService()),
                Security.TEACHER, Security.ADMIN
        );
        commands.addMapping(
                "course/delete", METHOD_GET,
                new CourseDeleteGetCommand(new CourseService()),
                Security.ADMIN
        );
        // ###### COURSE - REGISTER ######
        commands.addMapping(
                "course/register", METHOD_GET,
                new CourseRegisterCommand(new RegistrationService()),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        commands.addMapping(
                "course/register/cancel", METHOD_GET,
                new CourseRegisterCancelCommand(new RegistrationService()),
                Security.USER, Security.TEACHER, Security.ADMIN
        );
        // ###### LOCALE ######
        commands.addMapping(
                "locale", METHOD_GET,
                new ChangeLocaleGetCommand(),
                Security.UNKNOWN, Security.USER, Security.TEACHER, Security.ADMIN
        );
    }

    public static CommandKey getCommandKey(HttpServletRequest request) {
        RequestMethod requestMethod = RequestMethod.valueOf("METHOD_" + request.getMethod());
        String commandName = WebPaths.extractFinalPath(request.getRequestURI());
        log.info("CommandKey: {"+commandName+", "+requestMethod.name()+"}");
        return new CommandKey(commandName, requestMethod);
    }

    public static Command getCommand(CommandKey commandKey) {
        log.info("Try to find command '" + commandKey.getCommandName() +
                 "' with request method '" + commandKey.getRequestMethod().name() + "'");
        return commands.getCommandOrDefault(
                commandKey,
                commands.getCommand(new CommandKey(COMMAND_DOES_NOT_EXIST, METHOD_ANY))
        );
    }

    public static Set<Security> getAccessSet(CommandKey commandKey) {
        return  commands.getAccessSet(commandKey);
    }

    private CommandResolver() {
    }
}
