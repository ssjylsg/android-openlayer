package com.example.administrator.myapplication.Events;

import com.example.administrator.myapplication.Entity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class EventObject<T extends Entity> {
    private T source;

    public EventObject(T object) {
        this.source = object;
    }

    public T getSource() {
        return source;
    }
}
