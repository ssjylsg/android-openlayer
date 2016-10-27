package com.example.administrator.myapplication.Layers;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.administrator.myapplication.Entity;
import com.example.administrator.myapplication.NetPosaMap;

/**
 * Created by Administrator on 2016/10/18.
 */

public abstract class Layer extends Entity {

    private String name;
    @JSONField(serialize = false)
    protected NetPosaMap map;

    protected Layer(String name) {
        this.name = name;
    }

    /**
     * 显示或隐藏
     *
     * @param {boolean} display
     */
    public void display(boolean display) {
        this.ExecuteJs("display", display);
    }


    public void setMap(NetPosaMap map) {
        this.map = map;
    }
    public NetPosaMap getMap(  ) {
         return this.map;
    }
    protected Object ExecuteJs(String method, Object... args) {
        if (this.map != null) {
            return this.map.ExecuteJs(this, method, args);
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
