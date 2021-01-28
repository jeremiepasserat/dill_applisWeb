package com.dill.api_rest.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ReponseDefi {

    @Id
    int idDefi;

    String pseudoJoueur;

    int numQuestion;
    String imageReponse;


}
