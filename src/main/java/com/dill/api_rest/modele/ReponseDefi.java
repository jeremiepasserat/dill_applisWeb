package com.dill.api_rest.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class ReponseDefi {

    @Id
    int idReponse;

    int idDefi;

    String pseudoJoueur;

    int numQuestion;
    String texteReponse;
    LocalDate dateReponse;
    String imageReponse;


}
