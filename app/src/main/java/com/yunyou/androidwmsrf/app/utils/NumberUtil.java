package com.yunyou.androidwmsrf.app.utils;

import java.util.regex.Pattern;

/**
 * Author:      yunyou
 * Date:        2018-07-23 20:33
 * Description: 数字工具类
 */
public class NumberUtil {

    /**
     * 是否为整数
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /*
     * 判断是否为浮点数，包括double和float
     * @param str 传入的字符串
     * @return 是浮点数返回true,否则返回false
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}