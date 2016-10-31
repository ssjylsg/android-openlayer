package com.example.netposa.mobileMap.Events;

import com.example.netposa.mobileMap.Common.Entity;

/**
 * 事件源包装体
 */

public class EventObject<T extends Entity> {
    private T source;

    /**
     * 事件源包装体
     * @param object 事件源
     */
    public EventObject(T object) {
        this.source = object;
    }

    public T getSource() {
        return source;
    }
}
