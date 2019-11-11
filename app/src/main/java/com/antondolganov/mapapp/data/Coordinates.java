package com.antondolganov.mapapp.data;

public class Coordinates {

    private double Lng;
    private double Lat;

    public Coordinates(double lng, double lat) {
        Lng = lng;
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(int lng) {
        Lng = lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(int lat) {
        Lat = lat;
    }
}
