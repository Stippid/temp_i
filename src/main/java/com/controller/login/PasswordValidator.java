package com.controller.login;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class PasswordValidator {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!^\\&_.~*]).{8,28})";
    public static boolean validate(final String password) {
    	pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}