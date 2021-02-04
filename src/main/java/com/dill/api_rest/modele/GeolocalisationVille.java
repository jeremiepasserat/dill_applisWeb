package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Map;

@Entity
public class GeolocalisationVille {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int numeroPoint;
    String nomPoint;
    Coordonnees coordonneesPoint;

    public GeolocalisationVille(Coordonnees newPoint, String nomPoint) {
        this.nomPoint = nomPoint;
        this.coordonneesPoint = newPoint;
    }

    public GeolocalisationVille() {
    }

    public int getNumeroPoint() {
        return numeroPoint;
    }

    public String getNomPoint() {
        return nomPoint;
    }

    public Coordonnees getCoordonneesPoint() {
        return coordonneesPoint;
    }

    public void setNumeroPoint(int numeroPoint) {
        this.numeroPoint = numeroPoint;
    }

    public void setNomPoint(String nomPoint) {
        this.nomPoint = nomPoint;
    }

    public void setCoordonneesPoint(Coordonnees coordonneesPoint) {
        this.coordonneesPoint = coordonneesPoint;
    }
}
