package com.example.administrator.myapplication.Geometry;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.administrator.myapplication.Entity;

import com.example.administrator.myapplication.EventArgs;
import com.example.administrator.myapplication.NPEventListener;
import com.example.administrator.myapplication.NetPosaMap;


import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */

public abstract class Curve extends Entity {

    @JSONField(serialize = false)
    private NetPosaMap map;
    @JSONField(serialize = false)
    public double getArea() {
        Object result = ExecuteJs("getArea");
        if (result == null) {
            return 0;
        }
        return Integer.parseInt(result.toString());
    }
    @JSONField(serialize = false)
    public double getLength() {
        Object result = ExecuteJs("getLength");
        if (result == null) {
            return 0;
        }
        return Integer.parseInt(result.toString());
    }
    @JSONField(serialize = false)
    public double getPoints() {
        return 0;
    }

    public void hide() {
        this.ExecuteJs("hide");
    }

    public void refresh() {
        this.ExecuteJs("refresh");
    }

    public void setMap(NetPosaMap map) {
        this.map = map;
    }

    protected Object ExecuteJs(String method, Object... args) {
        if (this.map != null) {
            return this.map.ExecuteJs(this, method, args);
        }
        return null;
    }
    private Map<String,NPEventListener> events = new HashMap<String,NPEventListener>();
    public void addEventListener(String type, NPEventListener eventListener){
        this.ExecuteJs("register",type);
        this.events.put(type,eventListener);
    }

    public void processEvent(String event,Object ... args){
        this.events.get(event).processEvent(new EventObject(this),new EventArgs(args));
    }

}
