package com.example.netposa.mobileMap.Geometry;

/**
 * Created by Administrator on 2016/10/28.
 */

public class PolygonStyle extends Style {
    private String fillColor = "red";
    private double fillOpacity = 1.0;
    private double strokeWidth = 1.0;
    private String strokeDashstyle;
    private double pointRadius = 6;
    private String strokeLinecap;

    public PolygonStyle() {

    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public double getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(double fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * [dot | dash | dashdot | longdash | longdashdot | solid]
     */
    public String getStrokeDashstyle() {
        return strokeDashstyle;
    }

    public void setStrokeDashstyle(String strokeDashstyle) {
        this.strokeDashstyle = strokeDashstyle;
    }

    public double getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(double pointRadius) {
        this.pointRadius = pointRadius;
    }

    /**
     * [butt | round | square]
     */
    public String getStrokeLinecap() {
        return strokeLinecap;
    }

    public void setStrokeLinecap(String strokeLinecap) {
        this.strokeLinecap = strokeLinecap;
    }
}
