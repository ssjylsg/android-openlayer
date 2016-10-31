package com.example.netposa.mobileMap.Geometry;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.netposa.jsbridge.CallBackFunction;
import com.example.netposa.mobileMap.Common.Entity;
import com.example.netposa.mobileMap.Events.NPCallBackFunction;
import com.example.netposa.mobileMap.Events.NPEventListener;
import com.example.netposa.mobileMap.NetPosaMap;
import com.example.netposa.mobileMap.Common.Util;

/**
 * 覆盖物抽象类
 */

public abstract class Curve extends Entity {

    @JSONField(serialize = false)
    private NetPosaMap map;

    /**
     * 面积获取
     */
    @JSONField(serialize = false)
    public void getArea(NPCallBackFunction<Double> callBackFunction) {
        final NPCallBackFunction<Double> tempcallBackFunction = callBackFunction;
        this.ExecuteJs("getArea", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (tempcallBackFunction != null && !Util.isEmpty(data)) {
                    tempcallBackFunction.onCallBack((Double.parseDouble(data)));
                } else {
                    tempcallBackFunction.onCallBack(0.0);
                }
            }
        });
    }

    /**
     * 长度获取
     */
    @JSONField(serialize = false)
    public void getLength(NPCallBackFunction<Double> callBackFunction) {
        final NPCallBackFunction<Double> tempcallBackFunction = callBackFunction;
        this.ExecuteJs("getLength", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (tempcallBackFunction != null && !Util.isEmpty(data)) {
                    tempcallBackFunction.onCallBack((Double.parseDouble(data)));
                } else {
                    tempcallBackFunction.onCallBack(0.0);
                }
            }
        });
    }

//    @JSONField(serialize = false)
//    public double getPoints() {
//        return 0;
//    }

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

    protected void ExecuteJs(String method, CallBackFunction callBack, Object... args) {
        if (this.map != null) {
            this.map.ExecuteJs(this, method, callBack, args);
        }

    }

    /**
     * 注册事件
     *
     * @param type
     * @param eventListener
     */
    @Override
    public void addEventListener(String type, NPEventListener eventListener) {
        this.ExecuteJs("register", type);
        this.events.put(type, eventListener);
    }
}
