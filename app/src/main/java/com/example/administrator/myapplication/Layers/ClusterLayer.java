package com.example.administrator.myapplication.Layers;

import com.example.administrator.myapplication.EventArgs;
import com.example.administrator.myapplication.Events.EventObject;
import com.example.administrator.myapplication.Geometry.ClusterMarker;
import com.example.administrator.myapplication.NPEventListener;
import com.example.administrator.myapplication.Util;

import java.util.Arrays;


/**
 * Created by Administrator on 2016/10/18.
 */

public class ClusterLayer extends Layer {
    private ClusterLayerOptions options;

    public ClusterLayer(String name, ClusterLayerOptions options) {
        super(name);
        this.setOptions(options);
        this.setClassName("NPMobile.Layers.ClusterLayer");
    }

    public void addClusterMarkers(ClusterMarker[] clusterMarkers) {
        for (int i =0;i<clusterMarkers.length;i++){
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

    @Override
    public void addEventListener(String type, NPEventListener eventListener) {
        this.ExecuteJs("register", type);
        this.events.put(type, eventListener);
    }
}
