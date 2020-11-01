package org.elective.command.tools;

import java.util.Objects;

public class CommandKey {
    private final String commandName;
    private final RequestMethod requestMethod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandKey that = (CommandKey) o;
        return commandName.equals(that.commandName) &&
                requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName, requestMethod);
    }

    public CommandKey(String commandName, RequestMethod requestMethod) {
        this.commandName = commandName;
        this.requestMethod = requestMethod;
    }

    public String getCommandName() {
        return commandName;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }
}
