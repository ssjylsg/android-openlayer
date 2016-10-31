package com.example.netposa.mobileMap.Layers;

import com.example.netposa.mobileMap.Events.EventArgs;
import com.example.netposa.mobileMap.Events.EventObject;
import com.example.netposa.mobileMap.Events.NPEventListener;
import com.example.netposa.mobileMap.Geometry.ClusterMarker;
import com.example.netposa.mobileMap.Common.Util;

import java.util.Arrays;


/**
 * 聚合图层
 */
public class ClusterLayer extends Layer {
    private ClusterLayerOptions options;

    /**
     * 聚合图层
     * @param name 图层名称
     * @param options 参数配置
     */
    public ClusterLayer(String name, ClusterLayerOptions options) {
        super(name);
        this.setOptions(options);
        this.setClassName("NPMobile.Layers.ClusterLayer");
    }

    /**
     * 新增聚合Marker
     * @param clusterMarkers
     */
    public void addClusterMarkers(ClusterMarker[] clusterMarkers) {
        for (int i = 0; i < clusterMarkers.length; i++) {
            clusterMarkers[i].setLayer(this);
        }
        this.ExecuteJs("addOverlays", Arrays.asList(clusterMarkers));
    }

    @Override
    public void processEvent(String event, Object... args) {
        ClusterMarker marker = (ClusterMarker) Util.getEntity(args[0].toString());
        this.events.get(event).processEvent(new EventObject(marker), new EventArgs(null));
    }


    public ClusterLayerOptions getOptions() {
        return options;
    }

    public void setOptions(ClusterLayerOptions options) {
        this.options = options;
    }

    /**
     * 注册事件
     * @param type 如click
     * @param eventListener
     */
    @Override
    public void addEventListener(String type, NPEventListener eventListener) {
        this.ExecuteJs("register", type);
        this.events.put(type, eventListener);
    }
}
