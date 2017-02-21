package com.xiangsong.ticket.presenter.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by gala on 2016/9/9.
 */
public class GsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }

    public static Object fromJson(String str,Type type){
        return gson.fromJson(str, type);
    }
}
