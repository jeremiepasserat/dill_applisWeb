package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Defi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ElementCollection
    Collection<QuestionDefi> questions;

    public Collection<QuestionDefi> getQuestions() {
        return questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestions(Collection<QuestionDefi> questions) {
        this.questions = questions;
    }

    public void ajouterQuestion (QuestionDefi questionDefi){
        questions.add(questionDefi);
    }

    public void supprimerQuestion (int idQuestion){

        questions.removeIf(questionDefi -> questionDefi.numQuestion == idQuestion);
    }
}
