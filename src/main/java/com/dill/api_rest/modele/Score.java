package com.dill.api_rest.modele;

import javax.persistence.Embeddable;

@Embeddable
public class Score {
    private int idJeu;
    private int scoreJeu;
    private int tempsJeu;

    public Score() {
    }

    public int getIdJeu() {
        return idJeu;
    }

    public int getScoreJeu() {
        return scoreJeu;
    }

    public void setIdJeu(int idJeu) {
        this.idJeu = idJeu;
    }

    public void setScoreJeu(int scoreJeu) {
        this.scoreJeu = scoreJeu;
    }

    public int getTempsJeu() {
        return tempsJeu;
    }

    public void setTempsJeu(int tempsJeu) {
        this.tempsJeu = tempsJeu;
    }
}
