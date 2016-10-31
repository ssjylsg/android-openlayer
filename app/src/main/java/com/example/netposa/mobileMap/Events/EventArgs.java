package com.example.netposa.mobileMap.Events;

/**
 * JS 回调参数体
 */

public class EventArgs {
    private Object args;

    public EventArgs(Object e) {
        setArgs(e);
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }
}
