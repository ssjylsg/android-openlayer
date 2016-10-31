package com.example.netposa.mobileMap.Geometry;

import java.util.ArrayList;

/**
 * Polygon
 */

public class Polygon extends Curve {
    private ArrayList<Point> points;
    private PolygonStyle style;
    public Polygon(ArrayList<Point> points,PolygonStyle style){
        this.setPoints(points);
        this.setStyle(style);
        this.setClassName("NPMobile.Geometry.Polygon");
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public PolygonStyle getStyle() {
        return style;
    }

    public void setStyle(PolygonStyle style) {
        this.style = style;
    }
}
