package com.example.administrator.myapplication.Geometry;

import com.example.administrator.myapplication.Entity;
import com.example.administrator.myapplication.Layers.Layer;
import com.example.administrator.myapplication.NPEventListener;
import com.example.administrator.myapplication.NetPosaMap;
import com.example.administrator.myapplication.Point;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ClusterMarker extends Entity {
    private Point point;

    public ClusterMarker(Point point) {
        this.setClassName("NPMobile.Geometry.ClusterMarker");
        this.setPoint(point);
    }

    private Layer layer;

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    /**
     * 过时方法，请使用聚合图层的注册方法
     *
     * @param type
     * @param eventListener
     */
    @Deprecated
    public void addEventListener(String type, NPEventListener eventListener) {

    }

    @Deprecated
    public void processEvent(String event, Object... args) {

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void changeStyle(MarkerOptions options) {
        NetPosaMap map = this.layer.getMap();
        if (map != null) {
            map.ExecuteJs(this, "changeStyle", options);
        }
    }
}
