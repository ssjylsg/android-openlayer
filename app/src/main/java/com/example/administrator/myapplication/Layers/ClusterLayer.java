package com.example.administrator.myapplication.Layers;

/**
 * Created by Administrator on 2016/10/18.
 */

public class ClusterLayer extends Layer {
    private ClusterLayerOptions options;
    public  ClusterLayer(String name,ClusterLayerOptions options){
        super(name);
        this.options = options;
        this.setClassName("NPMobile.Layers.ClusterLayer");
    }


}
