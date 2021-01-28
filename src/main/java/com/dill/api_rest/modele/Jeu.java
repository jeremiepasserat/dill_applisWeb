package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idJeu;
    String nomJeu;
    @ElementCollection
    Map<Integer, String> imagesJeu;

}
