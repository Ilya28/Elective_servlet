package org.elective.controller;

import org.apache.log4j.Logger;
import org.elective.command.Command;
import org.elective.command.CommandResolver;
import org.elective.command.tools.CommandKey;
import org.elective.command.tools.RequestMethod;
import org.elective.dbtools.exceptions.ORMException;
import org.elective.service.WebPaths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    @Override
    public void init() throws ServletException {
        log.info("---===<< Servlete init >>===---");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, RequestMethod.METHOD_POST);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, RequestMethod.METHOD_GET);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, RequestMethod requestMethod)
            throws IOException, ServletException {
        log.info("\n======================================");
        String path = WebPaths.extractFinalPath(request.getRequestURI());
        log.info("Path = " + path);
        Command command = CommandResolver.getCommand(new CommandKey(path, requestMethod));
        log.info("Command: " + command.getClass().getName());
        String forward = "error";
        try {
            forward = command.execute(request, response);
        } catch (ORMException e) {
            e.printStackTrace();
        }
        if (WebPaths.isRedirect(forward)) {
            log.info("Redirect to page: '" + forward + "'");
            response.sendRedirect(WebPaths.getUrlFromRedirection(forward));
        } else {
            log.info("Load page: '" + forward + "'");
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }
}
