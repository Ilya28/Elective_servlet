package org.elective.service;

public class WebPaths {
    private static final String PREFIX = "/jsp/";
    private static final String POSTFIX = ".jsp";

    private static final int PATH_PREFIX = 31;

    public static String convertNameToPath(String name) {
        return PREFIX + name + POSTFIX;
    }

    public static String extractFinalPath(String rawPath) {
        return rawPath.substring(PATH_PREFIX);
    }
}
