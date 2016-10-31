package com.example.netposa.mobileMap.Layers;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.netposa.mobileMap.Common.Util;
import com.example.netposa.mobileMap.Geometry.Curve;

import java.util.ArrayList;

/**
 * 自定义图层
 */
public class CustomerLayer extends Layer {


    @JSONField(serialize = false)
    private ArrayList<String> overlayers;
    /**
     * 自定义图层
     *
     * @param name 图层名称
     */
    public CustomerLayer(String name) {
        super(name);
        this.setClassName("NPMobile.Layers.CustomerLayer");
        overlayers = new ArrayList<>();
    }

    /**
     * 新增覆盖物
     *
     * @param overlay
     */
    public void addOverlay(Curve overlay) {
        this.ExecuteJs("addOverlay", overlay);
        overlay.setMap(this.map);
        overlayers.add(overlay.getId());
    }

    /**
     * 移除覆盖物
     *
     * @param overlay
     */
    public void removeOverlay(Curve overlay) {
        this.ExecuteJs("removeOverlay", overlay);
        Util.removeEntity(overlay);
    }

    /**
     * 清除所有覆盖物
     */
    public void removeAllOverlays() {
        this.ExecuteJs("removeAllOverlays");
        Util.removKeys(overlayers);
        overlayers.clear();
    }


}
