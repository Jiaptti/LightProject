package com.viroyal.light.common.utils;

import java.util.regex.Pattern;

public class NumberUtils {
    public static boolean isNumber(String str) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isInt || isDouble;
    }

    public static boolean isNumber(String[] str) {
        for(int i = 0; i < str.length; i++){
            if(!isNumber(str[i])){
                return false;
            }
        }
        return true;
    }
}
