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
    String logoJeu;

    public Jeu(String nom, String logo) {
        this.nomJeu = nom;
        this.logoJeu = logo;
    }

    public String getNomJeu() {
        return nomJeu;
    }

    public Map<Integer, String> getImagesJeu() {
        return imagesJeu;
    }

    public String getLogoJeu() {
        return logoJeu;
    }

    public int getIdJeu() {
        return idJeu;
    }

    public void setIdJeu(int idJeu) {
        this.idJeu = idJeu;
    }

    public void setNomJeu(String nomJeu) {
        this.nomJeu = nomJeu;
    }

    public void setImagesJeu(Map<Integer, String> imagesJeu) {
        this.imagesJeu = imagesJeu;
    }

    public void setLogoJeu(String logoJeu) {
        this.logoJeu = logoJeu;
    }

    public void addImage(int numImage, String image){
        imagesJeu.put(numImage, image);
    }
}
