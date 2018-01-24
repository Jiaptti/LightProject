package com.viroyal.light.common.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher matcher = pattern.matcher(phone.trim());
        return matcher.matches();
    }

    public static boolean isRightNameFormat(String name) {
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*");
        Matcher matcher = pattern.matcher(name.trim());
        return matcher.matches();
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    public static boolean rightLength(String str, int min, int max) {
        if (str.trim().length() < min || str.trim().length() > max) {
            return false;
        }
        return true;
    }

    public static boolean isRightRegionDesc(String regionDesc) {
        if (StringUtils.equals(regionDesc.trim(), "地级市") || StringUtils.equals(regionDesc.trim(), "直辖市")
                || StringUtils.equals(regionDesc.trim(), "市辖区") || StringUtils.equals(regionDesc.trim(), "街道")) {
            return true;
        }
        return false;
    }

    public static boolean isRightPostalCode(String postCode) {
        String reg = "[1-9]\\d{5}";
        return Pattern.matches(reg, postCode);

    }


    public static Object copyProperties(Object dest, Object orig) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                Class destType = destDesc[i].getPropertyType();
                Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
                    if (!Collection.class.isAssignableFrom(origType)) {
                        Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                        PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dest;
    }

    /**   */
    /**
     * Copy properties of orig to dest Exception the Entity and Collection Type
     *
     * @param dest
     * @param orig
     * @param ignores
     * @return the dest bean
     */
    public static Object copyProperties(Object dest, Object orig, String[] ignores) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                if (contains(ignores, destDesc[i].getName())) {
                    continue;
                }
                Class destType = destDesc[i].getPropertyType();
                Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
                    if (!Collection.class.isAssignableFrom(origType)) {
                        Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                        PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dest;
    }

    static boolean contains(String[] ignores, String name) {
        boolean ignored = false;
        for (int j = 0; ignores != null && j < ignores.length; j++) {
            if (ignores[j].equals(name)) {
                ignored = true;
                break;
            }
        }
        return ignored;
    }
}
