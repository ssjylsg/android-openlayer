package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Point extends Entity {

    public Point(){
        this.setClassName("NPMobile.Geometry.Point");
    }
    public Point(double lon,double lat){
        this();
        this.lon = lon;
        this.lat = lat;
    }

    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }




}
