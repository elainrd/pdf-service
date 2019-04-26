package com.hhd.pdf.util;

import java.util.Collection;
import java.util.Map;

/**
 * FileName: ValidateHelper
 * Author:   胡侯东
 * Date:     2019-04-19  17:17
 * Description: 集合字符串判空工具类
 */
public class ValidateHelper {

    public ValidateHelper() {
    }

    public static boolean isEmptyString(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    public static boolean isNotEmptyCollection(Collection<?> collection) {
        return !isEmptyCollection(collection);
    }

    public static boolean isEmptyMap(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmptyMap(Map<?, ?> map) {
        return !isEmptyMap(map);
    }
    private static boolean isNormalized(String path) {
        if (path == null) {
            return true;
        }

        if (path.indexOf("//") > -1) {
            return false;
        }

        for (int j = path.length(); j > 0;) {
            int i = path.lastIndexOf('/', j - 1);
            int gap = j - i;

            if (gap == 2 && path.charAt(i + 1) == '.') {
                // ".", "/./" or "/."
                return false;
            } else if (gap == 3 && path.charAt(i + 1) == '.' && path.charAt(i + 2) == '.') {
                return false;
            }

            j = i;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNormalized("/captcha.jpg"));
    }
}
