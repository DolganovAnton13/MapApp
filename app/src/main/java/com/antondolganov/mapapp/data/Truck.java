package com.antondolganov.mapapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Truck {

    @SerializedName("RouteSegments")
    private List<RouteSegments> routeSegments;

    public Truck(List<RouteSegments> routeSegments) {
        this.routeSegments = routeSegments;
    }

    public List<RouteSegments> getRouteSegments() {
        return routeSegments;
    }

    public void setRouteSegments(List<RouteSegments> routeSegments) {
        this.routeSegments = routeSegments;
    }
}
