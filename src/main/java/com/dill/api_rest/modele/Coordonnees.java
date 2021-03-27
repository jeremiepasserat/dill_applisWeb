package com.dill.api_rest.modele;

import javax.persistence.Embeddable;

@Embeddable
public class Coordonnees {

    double longitude;
    double latitude;

    public Coordonnees() {
    }

    public Coordonnees(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
