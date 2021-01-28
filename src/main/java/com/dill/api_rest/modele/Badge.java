package com.dill.api_rest.modele;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Badge {

    @Id @GeneratedValue
    int id;
    String nom;
    String image;
}
