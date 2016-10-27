package com.example.administrator.myapplication;


import com.example.administrator.myapplication.Events.EventObject;

/**
 * Created by Administrator on 2016/10/21.
 */

public interface NPEventListener<T extends  Entity> extends java.util.EventListener {
    void processEvent(EventObject<T> sender, EventArgs e);
}
