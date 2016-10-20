package com.example.administrator.myapplication.Events;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2016/10/19.
 */

public class EventManager {
    private Queue<EventHandler> queue;

    public EventManager() {
        this.queue = new LinkedList<EventHandler>();
    }

    public void push(Object sender, String method, Object... args) {
        this.queue.offer(new EventHandler(sender, method, args, new Class[]{String.class}));
    }
    public boolean isEmpty(){
        return this.queue.isEmpty();
    }
    public void execute(){
        if(!this.queue.isEmpty()){
            EventHandler eventHandler = this.queue.poll();
            eventHandler.invoke();
        }
    }
}
