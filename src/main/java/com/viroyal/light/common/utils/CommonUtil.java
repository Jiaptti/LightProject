package com.viroyal.light.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static boolean isPhone(String phone){
        Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isRightNameFormat(String name){
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean rightLength(String str, int min, int max){
        if(str.length() < min || str.length() > max){
            return false;
        }
        return true;
    }
}
