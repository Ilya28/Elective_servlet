package org.elective.service;

public class WebPaths {
    private static final String PREFIX = "/jsp/";
    private static final String POSTFIX = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";

    private static final int PATH_PREFIX = 1; //31 for def deployment

    public static String nameToPath(String name) {
        return PREFIX + name + POSTFIX;
    }

    public static String extractFinalPath(String rawPath) {
        return rawPath.substring(PATH_PREFIX);
    }

    public static boolean isRedirect(String Url) {
        return Url.length() >= REDIRECT_PREFIX.length() &&
                REDIRECT_PREFIX.equals(Url.substring(0, REDIRECT_PREFIX.length()));
    }

    public static String getUrlFromRedirection(String Url) {
        return Url.length() >= REDIRECT_PREFIX.length()? Url.substring(REDIRECT_PREFIX.length()): Url;
    }
}
