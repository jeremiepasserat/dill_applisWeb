package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Map;

@Entity
public class GeolocalisationVille {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int numeroPoint;
    Coordonnees coordonneesPoint;

}
