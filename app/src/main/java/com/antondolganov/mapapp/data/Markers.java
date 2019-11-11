package com.antondolganov.mapapp.data;

import com.google.gson.annotations.SerializedName;

public class Markers {

    @SerializedName("Coordinates")
    private Coordinates coordinates;

    public Markers(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
