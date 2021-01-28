package com.dill.api_rest.modele;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@Embeddable
public class QuestionDefi {

    int numQuestion;
    String texteQuestion;
    String imageQuestion;
    String reponseQuestion;


}
