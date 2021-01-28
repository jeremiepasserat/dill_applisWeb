package com.dill.api_rest.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QrCode {

    @Id
    int numCode;
    String texteCode;
    int scoreCode;

}
