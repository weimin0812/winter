package com.free.winter.util;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class StringUtils {
    public static boolean hasLength(String s) {
        return hasLength((CharSequence) s);
    }

    public static boolean hasLength(CharSequence s) {
        return s != null && s.length() > 0;
    }

    public static boolean hasText(String s) {
        return hasText((CharSequence) s);
    }

    private static boolean hasText(CharSequence s) {
        if (!hasLength(s)) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            }
            else {
                index++;
            }
        }
        return sb.toString();
    }
}
