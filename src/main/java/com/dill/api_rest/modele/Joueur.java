package com.dill.api_rest.modele;


import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Joueur {

    @Id
    String pseudo;

    String password;

    LocalDate acceptationCGU;

    @Embedded
    Parent parent;

    @OneToMany (fetch = FetchType.LAZY)
    Collection<MessageCMJ> messageCMJS;

    @ElementCollection
    Collection<Score> scoresJeu;

    @Embedded
    Coordonnees coordonneesJoueur;

    @OneToMany
    Collection<Badge> badges;

    @OneToMany
    Collection<GeolocalisationVille> pointsVisites;

    @ElementCollection
    Collection<Pas> pasJoueur;

    public Joueur() {
    }

    public Joueur(String pseudo, String password, LocalDate acceptationCGU, Parent parent) {
        this.pseudo = pseudo;
        this.password = password;
        this.acceptationCGU = acceptationCGU;
        this.parent = parent;
    }

    public Collection<Pas> getPasJoueur() {
        return pasJoueur;
    }

    public void setPasJoueur(Collection<Pas> pasJoueur) {
        this.pasJoueur = pasJoueur;
    }

    public LocalDate getAcceptationCGU() {
        return acceptationCGU;
    }

    public void setAcceptationCGU(LocalDate acceptationCGU) {
        this.acceptationCGU = acceptationCGU;
    }

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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

    public void ajouterPointVisite (GeolocalisationVille pointVisite){

        this.pointsVisites.add(pointVisite);
    }

    public void modifierScore(int moisJeu, int idJeu, int scoreJeu, int tempsJeu){

        for (Score score: this.scoresJeu) {
            if (score.getMoisJeu() == moisJeu && score.getIdJeu() == idJeu){
                score.newBestScoreJeu(scoreJeu);
                score.ajouterTempsJeu(tempsJeu);
                return;
            }
        }
        this.scoresJeu.add(new Score(moisJeu, idJeu, scoreJeu, tempsJeu));
    }

}
