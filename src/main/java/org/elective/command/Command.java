package org.elective.command;

import org.elective.dbtools.exceptions.ORMException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ORMException;
}
