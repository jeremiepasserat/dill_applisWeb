package com.dill.api_rest.modele;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Badge {

    // L'id du badge est l'id du jeu associé (1 badge par jeu)
    @Id
    int id;
    String nom;
    String image;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
