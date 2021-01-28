package com.dill.api_rest.modele;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MessageCMJ {

    @Id
    int idMessage;
    String message;
    LocalDate dateMessage;
    @ManyToOne(fetch = FetchType.EAGER)
    Joueur joueur;


}
