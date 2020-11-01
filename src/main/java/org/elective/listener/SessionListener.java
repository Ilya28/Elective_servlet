package org.elective.listener;

import org.elective.service.Security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = new HashSet<>();
        httpSessionEvent.getSession().setAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER);
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute("username");
        loggedUsers.remove(userName);
        httpSessionEvent.getSession().setAttribute(Security.ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
    }
}
