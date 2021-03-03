package com.dill.api_rest.modele;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Score {

    private int moisJeu;
    private int idJeu;
    private int scoreJeu;
    private int tempsJeu;

    public Score(int moisJeu, int idJeu, int scoreJeu, int tempsJeu) {

        this.scoreJeu = scoreJeu;
        this.moisJeu = moisJeu;
        this.idJeu = idJeu;
        this.tempsJeu = tempsJeu;
    }

    public int getMoisJeu() {
        return moisJeu;
    }

    public void setMoisJeu(int moisJeu) {
        this.moisJeu = moisJeu;
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

    public void newBestScoreJeu(int scoreJeu){
        if (scoreJeu > this.scoreJeu){
            this.scoreJeu = scoreJeu;
        }
    }

    public void ajouterTempsJeu(int tempsJeu){
        this.tempsJeu += tempsJeu;
    }
}
