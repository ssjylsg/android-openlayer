package com.example.netposa.mobileMap.Events;


import com.example.netposa.mobileMap.Common.Entity;

/**
 * 事件监听器
 */

public interface NPEventListener<T extends Entity> extends java.util.EventListener {
    void processEvent(EventObject<T> sender, EventArgs e);
}
