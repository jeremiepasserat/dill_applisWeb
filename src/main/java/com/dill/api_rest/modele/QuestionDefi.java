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

    public QuestionDefi(int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion) {
        this.numQuestion = numQuestion;
        this.texteQuestion = texteQuestion;
        this.imageQuestion = imageQuestion;
        this.reponseQuestion = reponseQuestion;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public String getImageQuestion() {
        return imageQuestion;
    }

    public String getReponseQuestion() {
        return reponseQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public void setImageQuestion(String imageQuestion) {
        this.imageQuestion = imageQuestion;
    }

    public void setReponseQuestion(String reponseQuestion) {
        this.reponseQuestion = reponseQuestion;
    }
}
