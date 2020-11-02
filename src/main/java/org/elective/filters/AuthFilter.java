package org.elective.filters;

import org.apache.log4j.Logger;
import org.elective.command.CommandResolver;
import org.elective.controller.Controller;
import org.elective.service.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthFilter implements Filter {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("--------- auth filter ---------");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();

        Security userRole = Security.getUserRole(request);
        log.info("User role: " + userRole.name());
        Set<Security> allowedRoles = CommandResolver.getAccessSet(CommandResolver.getCommandKey(request));
        log.info("Allowed roles: " + allowedRoles.toString());
        if (allowedRoles.contains(userRole)) {
            log.info("ALLOWED");
            request.setAttribute("role", userRole.name());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("DENIED");
            response.getWriter().append("Access denied " +
                    "<br/> <a href=\"/login\">Login</a> <br/> <a href=\"/home\">Home</a>");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
