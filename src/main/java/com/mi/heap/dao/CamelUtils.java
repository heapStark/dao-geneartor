package com.mi.heap.dao;

/**
 * Created by WZL on 2018/3/25.
 */
public class CamelUtils {
    /**
     * 下划线命名转为驼峰命名
     *
     * @param para
     * @return
     */
    public static String toCamelString(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 字符串首字母大写
     * @param para
     * @return
     */
    public static String toFirstUperString(String para) {
        StringBuilder result = new StringBuilder();
        result.append(para.substring(0, 1).toUpperCase());
        result.append(para.substring(1));
        return result.toString();
    }

    /**
     * 首字母小写
     * @param para
     * @return
     */
    public static String toFirstLowerString(String para) {
        StringBuilder result = new StringBuilder();
        result.append(para.substring(0, 1).toLowerCase());
        result.append(para.substring(1));
        return result.toString();
    }

}
