package com.mi.heap.util;


public class Configure {
    public static boolean COMMENT;
    public static boolean SETTERGETTER;
    public static boolean TOSTRING;
    public static Class BUILDER;
    public static Configure configure = new Configure();

    public static Configure openComment() {
        COMMENT = true;
        return configure;
    }

    public static Configure openSetGet() {
        SETTERGETTER = true;
        return configure;
    }

    public static Configure openToString(Class builder) {
        BUILDER = builder;
        TOSTRING = true;
        return configure;
    }
}
