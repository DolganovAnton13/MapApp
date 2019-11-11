package com.antondolganov.mapapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteSegments {

    @SerializedName("Markers")
    private List<Markers> markers;

    public RouteSegments(List<Markers> markers) {
        this.markers = markers;
    }

    public List<Markers> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Markers> markers) {
        this.markers = markers;
    }
}
