package com.example.netposa.mobileMap.Common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 帮助类
 */

public class Util {
    private static HashMap<String, Entity> _entities = new HashMap<>();

    public static Entity getEntity(String id) {
        return _entities.get(id);
    }

    public static void AddEntity(Entity entity) {
        _entities.put(entity.getId(), entity);
    }

    public static void removeEntity(Entity entity) {
        _entities.remove(entity.getId());
    }

    /**
     * 产生GUID
     *
     * @return {String}
     */
    public static String generateId() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * 解析JSON 字符串
     *
     * @param jsonString
     * @return Object
     */
    public static Object parseObject(String jsonString) {
        return com.alibaba.fastjson.JSON.parse(jsonString);
    }

    /**
     * 字符串拼接，相当于JS 的join 函数
     *
     * @param o
     * @param flag
     * @return String
     */
    public static String join(Object[] o, String flag) {
        StringBuffer str_buff = new StringBuffer();
        for (int i = 0, len = o.length; i < len; i++) {
            str_buff.append(o[i].toString());
            if (i < len - 1) str_buff.append(flag);
        }
        return str_buff.toString();
    }


    /**
     * 打印日志
     *
     * @param tag
     * @param msg
     */
    public static void Info(String tag, String msg) {
        if (!isEmpty(msg)) {
            android.util.Log.i(tag, msg);
        }
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0 || value.trim() == "";
    }

    public static void removKeys(ArrayList<String> entityIds) {
        for (String key : entityIds) {
            _entities.remove(key);
        }
    }
}
