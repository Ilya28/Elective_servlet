package org.elective.command;

import org.elective.command.commands.NotExistCommand;
import org.elective.command.tools.CommandKey;
import org.elective.command.tools.CommandsMap;
import org.elective.command.tools.RequestMethod;
import static org.elective.command.tools.RequestMethod.*;

public class CommandResolver {
    private static final CommandKey COMMAND_DOES_NOT_EXIST =
            new CommandKey("Not exist", METHOD_ANY);

    private static final CommandsMap commands = new CommandsMap();
    static {
        commands.addMapping(COMMAND_DOES_NOT_EXIST, new NotExistCommand());
    }

    public static Command get(String commandName, RequestMethod requestMethod) {
        return commands.getCommandOrDefault(
                commandName,
                requestMethod,
                commands.getCommand(COMMAND_DOES_NOT_EXIST)
        );
    }

    private CommandResolver() {
    }
}
