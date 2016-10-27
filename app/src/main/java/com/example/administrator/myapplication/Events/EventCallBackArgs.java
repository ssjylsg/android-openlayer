package com.example.administrator.myapplication.Events;

/**
 * Created by Administrator on 2016/10/26.
 */

public class EventCallBackArgs {
    private String id;
    private String eventType;
    private Object[] args;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
