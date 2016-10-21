package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2016/10/21.
 */

public   class EventArgs {
    private Object args;
    public EventArgs(Object e){
        setArgs(e);
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }
}
