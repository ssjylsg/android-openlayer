package com.example.administrator.myapplication;


/**
 * Created by Administrator on 2016/10/17.
 */

public abstract class Entity  {
    private String id;
    private String className;


    protected void setClassName(String className) {
        this.className = className;
    }
    public String getClassName(){
        return  this.className;
    }

    protected Entity() {
        this.id = Util.generateId();
        Util.AddEntity(this);
    }

    public String getId() {
        return this.id;
    }

    protected void setId(String id) {
        this.id = id;
    }



    protected String toJson() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    @Override
    public String toString(){
        return this.toJson();
    }

}
