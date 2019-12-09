package com.billy.billyServices.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    private static final String BEARER_REGEX = "^Bearer:\\s\\w.+";

    public static boolean authorizationHeaderValid(final String auth) {

        final Pattern authHeaderPattern = Pattern.compile(BEARER_REGEX);
        final Matcher matcher = authHeaderPattern.matcher(auth);
        if (matcher.find()) return true;
        else return false;
    }
}
