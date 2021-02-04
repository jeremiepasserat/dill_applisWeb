package com.dill.api_rest.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QrCode {

    @Id
    int numCode;
    String texteCode;
    int scoreCode;

    public QrCode() {
    }

    public int getNumCode() {
        return numCode;
    }

    public String getTexteCode() {
        return texteCode;
    }

    public int getScoreCode() {
        return scoreCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public void setTexteCode(String texteCode) {
        this.texteCode = texteCode;
    }

    public void setScoreCode(int scoreCode) {
        this.scoreCode = scoreCode;
    }
}
