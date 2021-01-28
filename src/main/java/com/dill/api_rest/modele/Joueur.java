package com.dill.api_rest.modele;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
public class Joueur {

    @Id
    String pseudo;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "joueur",  cascade = CascadeType.ALL)
    Collection<MessageCMJ> messageCMJS;

    @ElementCollection
    Map<Integer, Integer> scoresJeu;

    @ElementCollection
    Map<Integer, Integer> tempsParJeu;

    @OneToMany
    Collection<Badge> badges;

    @OneToMany
    Collection<GeolocalisationVille> pointsVisites;

}
