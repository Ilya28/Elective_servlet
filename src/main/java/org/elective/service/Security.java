package org.elective.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public enum  Security {
    UNKNOWN, USER, TEACHER, ADMIN;

    public static final String ATTRIBUTE_ROLE_HOLDER = "user_role";
    public static final String ATTRIBUTE_LOGGED_USERS_HOLDER = "logged_users";
    public static final String ATTRIBUTE_USERNAME_HOLDER = "username";

    public static boolean checkUserIsLogged(HttpServletRequest request, String username) {
        Object loggedUsersObj = request.getSession().getServletContext().getAttribute(ATTRIBUTE_LOGGED_USERS_HOLDER);
        HashSet<String> loggedUsers;
        if (loggedUsersObj instanceof HashSet)
            loggedUsers = (HashSet<String>) loggedUsersObj;
        else
            loggedUsers = new HashSet<>();

        if(loggedUsers.stream().anyMatch(username::equals))
            return true;
        loggedUsers.add(username);
        request.getSession().getServletContext().setAttribute(ATTRIBUTE_LOGGED_USERS_HOLDER, loggedUsers);
        return false;
    }

    public static void setUserRole(HttpServletRequest request, Security role, String username) {
        request.getSession().setAttribute(ATTRIBUTE_ROLE_HOLDER, role.name());
        request.getServletContext().setAttribute(ATTRIBUTE_USERNAME_HOLDER, username);
    }

    public static Security getUserRole(HttpServletRequest request) {
        String roleAsString = (String) request.getSession().getAttribute(ATTRIBUTE_ROLE_HOLDER);
        if (roleAsString == null || roleAsString.isEmpty())
            return Security.UNKNOWN;
        return Security.valueOf(roleAsString);
    }
}
