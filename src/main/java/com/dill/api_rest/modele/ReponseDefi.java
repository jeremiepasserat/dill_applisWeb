package com.dill.api_rest.modele;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ReponseDefi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idReponse;

    int idDefi;

    String pseudoJoueur;

    int numQuestion;
    String texteReponse;
    LocalDate dateReponse;
    String imageReponse;

    public int getIdReponse() {
        return idReponse;
    }

    public int getIdDefi() {
        return idDefi;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public String getTexteReponse() {
        return texteReponse;
    }

    public LocalDate getDateReponse() {
        return dateReponse;
    }

    public String getImageReponse() {
        return imageReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public void setIdDefi(int idDefi) {
        this.idDefi = idDefi;
    }

    public void setPseudoJoueur(String pseudoJoueur) {
        this.pseudoJoueur = pseudoJoueur;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }

    public void setDateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
    }

    public void setImageReponse(String imageReponse) {
        this.imageReponse = imageReponse;
    }
}
