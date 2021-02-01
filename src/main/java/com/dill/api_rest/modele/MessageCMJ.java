package com.dill.api_rest.modele;

import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MessageCMJ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idMessage;
    String message;
    LocalDate dateMessage;
    @ManyToOne(fetch = FetchType.EAGER)
    Joueur joueur;

    public MessageCMJ(String message, Joueur joueur) {
        this.message = message;
        this.joueur = joueur;
        this.dateMessage = LocalDate.now();
    }

    public LocalDate getDateMessage() {
        return dateMessage;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public String getMessage() {
        return message;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateMessage(LocalDate dateMessage) {
        this.dateMessage = dateMessage;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
