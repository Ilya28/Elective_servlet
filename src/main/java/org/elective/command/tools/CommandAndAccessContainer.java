package org.elective.command.tools;

import org.elective.command.Command;
import org.elective.service.Security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandAndAccessContainer {
    private final Set<Security> accessSet;
    private final Command command;

    public CommandAndAccessContainer(Command command, Security...securities) {
        this.accessSet = new HashSet<>(Arrays.asList(securities));
        this.command = command;
    }

    public Set<Security> getAccessSet() {
        return accessSet;
    }

    public Command getCommand() {
        return command;
    }
}
