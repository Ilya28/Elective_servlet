package org.elective.controller;

import org.elective.command.Command;
import org.elective.command.CommandResolver;
import org.elective.command.tools.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private final int PATH_PREFIX = 31;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("service()");
        super.service(request, response);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init()");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost()");
        processRequest(request, response, RequestMethod.METHOD_POST);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet()");
        processRequest(request, response, RequestMethod.METHOD_GET);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, RequestMethod requestMethod) {
        String path = request.getRequestURI().substring(PATH_PREFIX);
        System.out.println("Path = " + path);

        Command command = CommandResolver.get(path, requestMethod);

        command.execute(request, response);
    }
}
