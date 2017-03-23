package com.appsfromveena.stability.howstable.util;

/**
 * Created by veena on 1/3/17.
 */

public class TextUtil {
    public static boolean isStringNotNullOrEmpty(String data){
        return !(data == null  || data.equalsIgnoreCase("null") ||data.length()<=0);
    }

    public static boolean isStringNotEmpty(String data){
        return !(data.length() <= 0);
    }

    public static boolean isStringNotNull(String data){
        return !(data == null  || data.equalsIgnoreCase("null"));
    }
}

