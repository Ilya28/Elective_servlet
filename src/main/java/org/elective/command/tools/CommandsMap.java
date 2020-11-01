package org.elective.command.tools;

import org.elective.command.Command;
import org.elective.service.Security;

import java.util.*;

public class CommandsMap extends HashMap<CommandKey, CommandAndAccessContainer> {
    public void addMapping(CommandKey commandKey, CommandAndAccessContainer command) {
        this.put(commandKey, command);
    }

    public void addMapping(String commandName, RequestMethod requestMethod, Command command, Security...securities) {
        this.addMapping(
                new CommandKey(commandName, requestMethod),
                new CommandAndAccessContainer(command, securities));
    }

    public Command getCommand(CommandKey commandKey) {
        return this.get(commandKey).getCommand();
    }

    public Command getCommandOrDefault(CommandKey commandKey, Command defaultCommand) {
        Optional<Command> command = Optional.ofNullable(get(commandKey).getCommand());
        return command.orElse(defaultCommand);
    }

    public Set<Security> getAccessSet(CommandKey commandKey) {
        Optional<CommandAndAccessContainer> commandAndAccessContainer =
                Optional.ofNullable(this.get(commandKey));
        if (commandAndAccessContainer.isPresent())
            return commandAndAccessContainer.get().getAccessSet();
        else
            return new HashSet<>();
    }
}
