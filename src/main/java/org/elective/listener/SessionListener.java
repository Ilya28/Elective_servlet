package org.elective.listener;

import org.apache.log4j.Logger;
import org.elective.command.commands.LogOutCommand;
import org.elective.service.Security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    private static final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = new HashSet<>();
        httpSessionEvent.getSession().setAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
        httpSessionEvent.getSession().setAttribute("locale", "en");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession()
                .getServletContext()
                .getAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER);
        String username = (String) httpSessionEvent
                .getSession()
                .getAttribute(Security.ATTRIBUTE_USERNAME_HOLDER);
        loggedUsers.remove(username);
        log.info("logged users: " + loggedUsers.toString());
        httpSessionEvent
                .getSession()
                .getServletContext()
                .setAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
    }
}
