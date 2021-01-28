package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.List;

@Entity
public class Defi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ElementCollection
    List<QuestionDefi> questions;


}
