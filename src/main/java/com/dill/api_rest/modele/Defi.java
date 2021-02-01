package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.List;

@Entity
public class Defi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ElementCollection
    Defi questions;

    public Defi getQuestions() {
        return questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestions(Defi questions) {
        this.questions = questions;
    }

    public void ajouterQuestion (QuestionDefi questionDefi){
        questions.add(questionDefi);
    }

    public void supprimerQuestion (int idQuestion){

        questions.removeIf(questionDefi -> questionDefi.numQuestion == idQuestion);
    }
}
