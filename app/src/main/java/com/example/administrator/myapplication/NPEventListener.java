package com.example.administrator.myapplication;


import java.util.EventObject;

/**
 * Created by Administrator on 2016/10/21.
 */

public interface NPEventListener extends java.util.EventListener {
    void processEvent(EventObject sender, EventArgs e);
}
