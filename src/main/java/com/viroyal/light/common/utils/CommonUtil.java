package com.viroyal.light.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static boolean isPhone(String phone){
        Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher matcher = pattern.matcher(phone.trim());
        return matcher.matches();
    }

    public static boolean isRightNameFormat(String name){
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*");
        Matcher matcher = pattern.matcher(name.trim());
        return matcher.matches();
    }

    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    public static boolean rightLength(String str, int min, int max){
        if(str.trim().length() < min || str.trim().length() > max){
            return false;
        }
        return true;
    }

    public static boolean isRightRegionDesc(String regionDesc){
        if(StringUtils.equals(regionDesc.trim(), "地级市") || StringUtils.equals(regionDesc.trim(), "直辖市")
                || StringUtils.equals(regionDesc.trim(), "市辖区") || StringUtils.equals(regionDesc.trim(), "街道")){
            return true;
        }
        return false;
    }

    public static boolean isRightPostalCode(String postCode){
        String reg = "[1-9]\\d{5}";
        return Pattern.matches(reg, postCode);

    }
}
