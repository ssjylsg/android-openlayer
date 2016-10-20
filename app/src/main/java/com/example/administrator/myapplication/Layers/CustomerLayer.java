package com.example.administrator.myapplication.Layers;

import com.example.administrator.myapplication.Geometry.Curve;

/**
 * Created by Administrator on 2016/10/18.
 */

public class CustomerLayer extends Layer {


    public CustomerLayer(String name ) {
        super(name);
        this.setClassName("NPMobile.Layers.CustomerLayer");
    }

    public void addOverlay(Curve overlay){
        this.ExecuteJs("addOverlay",overlay);
        overlay.setMap(this.map);
    }


}
