package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
public class Joueur {

    @Id
    String pseudo;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "joueur",  cascade = CascadeType.ALL)
    Collection<MessageCMJ> messageCMJS;

    @ElementCollection
    Collection<Score> scoresJeu;

    @Embedded
    Coordonnees coordonneesJoueur;

    @OneToMany
    Collection<Badge> badges;

    @OneToMany
    Collection<GeolocalisationVille> pointsVisites;

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Coordonnees getCoordonneesJoueur() {
        return coordonneesJoueur;
    }

    public Collection<Score> getScoresJeu() {
        return scoresJeu;
    }

    public Collection<MessageCMJ> getMessageCMJS() {
        return messageCMJS;
    }



    public Collection<Badge> getBadges() {
        return badges;
    }

    public Collection<GeolocalisationVille> getPointsVisites() {
        return pointsVisites;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMessageCMJS(Collection<MessageCMJ> messageCMJS) {
        this.messageCMJS = messageCMJS;
    }

    public void setScoresJeu(Collection<Score> scoresJeu) {
        this.scoresJeu = scoresJeu;
    }



    public void setCoordonneesJoueur(Coordonnees coordonneesJoueur) {
        this.coordonneesJoueur = coordonneesJoueur;
    }

    public void setBadges(Collection<Badge> badges) {
        this.badges = badges;
    }

    public void setPointsVisites(Collection<GeolocalisationVille> pointsVisites) {
        this.pointsVisites = pointsVisites;
    }
}
