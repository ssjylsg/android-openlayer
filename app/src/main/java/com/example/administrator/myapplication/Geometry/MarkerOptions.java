package com.example.administrator.myapplication.Geometry;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MarkerOptions {
    private String externalGraphic;
    private Double graphicXOffset = null;
    private Double graphicYOffset = null;
    private Double graphicWidth;
    private Double graphicHeight;
    private Integer graphicZIndex = null;
    private String label;
    private String labelAlign = "cm";
    private Double labelXOffset;
    private Double labelYOffset;
    private String fontColor;
    private String fontSize;
    private String fontFamily;

    public MarkerOptions(String externalGraphic, Double graphicWidth, Double graphicHeight) {
        this.externalGraphic = externalGraphic;
        this.graphicHeight = graphicHeight;
        this.graphicWidth = graphicWidth;
        this.graphicXOffset = -graphicWidth / 2;
        this.graphicYOffset = -graphicHeight / 2;
    }

    public MarkerOptions() {

    }

    public String getExternalGraphic() {
        return externalGraphic;
    }

    public void setExternalGraphic(String externalGraphic) {
        this.externalGraphic = externalGraphic;
    }

    public Double getGraphicXOffset() {
        return graphicXOffset;
    }

    public void setGraphicXOffset(Double graphicXOffset) {
        this.graphicXOffset = graphicXOffset;
    }

    public Double getGraphicYOffset() {
        return graphicYOffset;
    }

    public void setGraphicYOffset(Double graphicYOffset) {
        this.graphicYOffset = graphicYOffset;
    }

    public double getGraphicWidth() {
        return graphicWidth;
    }

    public void setGraphicWidth(double graphicWidth) {
        this.graphicWidth = graphicWidth;
    }

    public double getGraphicHeight() {
        return graphicHeight;
    }

    public void setGraphicHeight(double graphicHeight) {
        this.graphicHeight = graphicHeight;
    }

    public Integer getGraphicZIndex() {
        return graphicZIndex;
    }

    public void setGraphicZIndex(Integer graphicZIndex) {
        this.graphicZIndex = graphicZIndex;
    }

    public void setGraphicWidth(Double graphicWidth) {
        this.graphicWidth = graphicWidth;
    }

    public void setGraphicHeight(Double graphicHeight) {
        this.graphicHeight = graphicHeight;
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

    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }
}
