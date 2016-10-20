package com.example.administrator.myapplication.Geometry;

import com.example.administrator.myapplication.Point;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Marker extends Curve {
    private Point point;
    private  MarkerOptions options;
    public Marker(Point point,MarkerOptions options){
        this.setClassName("NPMobile.Geometry.Marker");
        this.setPoint(point);
        this.setOptions(options);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public MarkerOptions getOptions() {
        return options;
    }

    public void setOptions(MarkerOptions options) {
        this.options = options;
    }


}
