package com.yunyou.androidwmsrf.app.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 对象属性赋值
 *
 * @author WMJ
 * @version 2019/07/11
 */
public class CopyPropertiesUtil {
    /**
     * 利用反射实现对象之间属性复制
     *
     * @param from
     * @param to
     */
    public static void copyProperties(Object from, Object to) {
        try {
            copyPropertiesExclude(from, to, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制对象属性
     *
     * @param from
     * @param to
     * @param excludsArray 排除属性列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private static void copyPropertiesExclude(Object from, Object to, String[] excludsArray) throws Exception {
        List<String> excludesList = null;
        if (excludsArray != null && excludsArray.length > 0) {
            excludesList = Arrays.asList(excludsArray);
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (Method method : fromMethods) {
            fromMethod = method;
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get") || fromMethodName.contains("getId"))
                continue;
            //排除列表检测
            if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null)
                continue;
            Object value = fromMethod.invoke(from);
            if (value == null)
                continue;
            //集合类判空处理
            if (value instanceof Collection) {
                Collection newValue = (Collection) value;
                if (newValue.size() <= 0)
                    continue;
            }
            toMethod.invoke(to, value);
        }
    }

    /**
     * 对象属性值复制，仅复制指定名称的属性值
     *
     * @param from
     * @param to
     * @param includsArray
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void copyPropertiesInclude(Object from, Object to, String[] includsArray) throws Exception {
        List<String> includesList = null;
        if (includsArray != null && includsArray.length > 0) {
            includesList = Arrays.asList(includsArray);    //构造列表对象
        } else {
            return;
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (Method method : fromMethods) {
            fromMethod = method;
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get"))
                continue;
            //排除列表检测
            String str = fromMethodName.substring(3);
            if (!includesList.contains(str.substring(0, 1).toLowerCase() + str.substring(1))) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null)
                continue;
            Object value = fromMethod.invoke(from);
            if (value == null)
                continue;
            //集合类判空处理
            if (value instanceof Collection) {
                Collection newValue = (Collection) value;
                if (newValue.size() <= 0)
                    continue;
            }
            toMethod.invoke(to, value);
        }
    }


    /**
     * 从方法数组中获取指定名称的方法
     *
     * @param methods
     * @param name
     * @return
     */
    public static Method findMethodByName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name))
                return method;
        }
        return null;
    }
}