package com.billy.billyServices.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods for pattern (regex) check
 */
public class PatternUtil {
    private static final String BEARER_REGEX = "^Bearer:\\s\\w.+";

    /**
     * Method to check Authorization header validity
     *
     * @param auth {@link String} the auth (Authorization header content)
     * @return {@link boolean} the boolean representing validity status
     */
    public static boolean authorizationHeaderValid(final String auth) {

        final Pattern authHeaderPattern = Pattern.compile(BEARER_REGEX);
        final Matcher matcher = authHeaderPattern.matcher(auth);
        if (matcher.find()) return true;
        else return false;
    }
}
