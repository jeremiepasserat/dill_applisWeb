package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.List;

@Entity
public class Defi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ElementCollection
    List<QuestionDefi> questions;

    public List<QuestionDefi> getQuestions() {
        return questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestions(List<QuestionDefi> questions) {
        this.questions = questions;
    }

    public void ajouterQuestion (QuestionDefi questionDefi){
        questions.add(questionDefi);
    }

    public void supprimerQuestion (QuestionDefi questionDefi){
        questions.remove(questionDefi);
    }
}
