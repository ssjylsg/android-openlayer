package com.example.netposa.mobileMap.Layers;

import com.example.netposa.mobileMap.Common.Image;
import com.example.netposa.mobileMap.Common.Size;

/**
 * 聚合图层参数配置
 */

public class ClusterLayerOptions {
    private int selectZoom;
    private int threshold;
    private int distance = 200;
    private int maxZoom;
    private Integer minZoom = null;
    private boolean isAsynchronous = true;
    private String fontColor;
    private String fontSize;
    private String customLabelFontColor;
    private Size customLabelOffset;
    private Image ClusterImage;
    private Image SingleImage;


    public int getSelectZoom() {
        return selectZoom;
    }

    public void setSelectZoom(int selectZoom) {
        this.selectZoom = selectZoom;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    public Integer getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
    }

    public boolean isAsynchronous() {
        return isAsynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        isAsynchronous = asynchronous;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getCustomLabelFontColor() {
        return customLabelFontColor;
    }

    public void setCustomLabelFontColor(String customLabelFontColor) {
        this.customLabelFontColor = customLabelFontColor;
    }

    public Size getCustomLabelOffset() {
        return customLabelOffset;
    }

    public void setCustomLabelOffset(Size customLabelOffset) {
        this.customLabelOffset = customLabelOffset;
    }

    public Image getClusterImage() {
        return ClusterImage;
    }

    /**
     * 设置聚合图片路径和图片大小
     * @param clusterImage
     */
    public void setClusterImage(Image clusterImage) {
        ClusterImage = clusterImage;
    }

    public Image getSingleImage() {
        return SingleImage;
    }

    /**
     * 设置聚合散开点图片路径和图片大小
     * @param singleImage
     */
    public void setSingleImage(Image singleImage) {
        SingleImage = singleImage;
    }
}
