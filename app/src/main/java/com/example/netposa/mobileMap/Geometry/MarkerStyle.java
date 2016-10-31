package com.example.netposa.mobileMap.Geometry;

/**
 * MarkerOptions 配置
 */

public class MarkerStyle extends  Style {
    private String externalGraphic;
    private Double graphicXOffset = null;
    private Double graphicYOffset = null;
    private Double graphicWidth;
    private Double graphicHeight;
    private Integer graphicZIndex = null;



    /**
     * MarkerOptions
     * @param externalGraphic 图片URL
     * @param graphicWidth  图片宽度
     * @param graphicHeight 图片高度
     */
    public MarkerStyle(String externalGraphic, Double graphicWidth, Double graphicHeight) {
        this.externalGraphic = externalGraphic;
        this.graphicHeight = graphicHeight;
        this.graphicWidth = graphicWidth;
        this.graphicXOffset = -graphicWidth / 2;
        this.graphicYOffset = -graphicHeight / 2;
    }

    public MarkerStyle() {

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

    public void setGraphicWidth(Double graphicWidth) {
        this.graphicWidth = graphicWidth;
    }

    public void setGraphicWidth(double graphicWidth) {
        this.graphicWidth = graphicWidth;
    }

    public double getGraphicHeight() {
        return graphicHeight;
    }

    public void setGraphicHeight(Double graphicHeight) {
        this.graphicHeight = graphicHeight;
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

}
