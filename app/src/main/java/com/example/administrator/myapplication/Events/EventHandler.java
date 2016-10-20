package com.example.administrator.myapplication.Events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/10/19.
 */

public class EventHandler {
    private Object sender = null;
    private String methodName = null;
    private Object[] params;
    private Class[] paramTypes;

    public EventHandler(Object sender, String methodName, Object[] params, Class[] paramTypes) {
        this.sender = sender;
        this.params = params;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
    }

    public void invoke() {
        Method method = null;
        try {
            method = sender.getClass().getMethod(this.methodName, this.paramTypes);
            if (method != null) {
                method.invoke(sender, this.params);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
