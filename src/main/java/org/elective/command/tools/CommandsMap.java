package org.elective.command.tools;

import org.elective.command.Command;

import java.util.HashMap;

public class CommandsMap extends HashMap<CommandKey, Command> {
    public void addMapping(CommandKey commandKey, Command command) {
        this.put(commandKey, command);
    }

    public void addMapping(String commandName, RequestMethod requestMethod, Command command) {
        this.addMapping(new CommandKey(commandName, requestMethod), command);
    }

    public void addMapping(String commandName, Command command) {
        this.addMapping(commandName, RequestMethod.METHOD_ANY, command);
    }

    public Command getCommand(CommandKey commandKey) {
        return this.get(commandKey);
    }
    public Command getCommand(String commandName, RequestMethod requestMethod) {
        return this.get(new CommandKey(commandName, requestMethod));
    }

    public Command getCommandOrDefault(String commandName, RequestMethod requestMethod, Command command) {
        return this.getOrDefault(new CommandKey(commandName, requestMethod), command);
    }
}
