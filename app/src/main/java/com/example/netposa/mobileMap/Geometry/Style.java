package com.example.netposa.mobileMap.Geometry;

/**
 * 覆盖物样式
 */

public class Style {
    private String label;
    private String labelAlign = "cm";
    private Double labelXOffset = 0.0;
    private Double labelYOffset = 0.0;
    private String fontColor = "white";
    private String fontSize = "14px";
    private String fontFamily ;

    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelAlign() {
        return labelAlign;
    }

    public void setLabelAlign(String labelAlign) {
        this.labelAlign = labelAlign;
    }

    public Double getLabelXOffset() {
        return labelXOffset;
    }

    public void setLabelXOffset(Double labelXOffset) {
        this.labelXOffset = labelXOffset;
    }

    public Double getLabelYOffset() {
        return labelYOffset;
    }

    public void setLabelYOffset(Double labelYOffset) {
        this.labelYOffset = labelYOffset;
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

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
}
