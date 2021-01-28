package com.dill.api_rest.service;

import com.dill.api_rest.modele.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface Services {

    // pour charger les informations d'un joueur
    Joueur getJoueurByPseudo (String pseudo);

    // pour récupérer les scores
    Map<String, Integer> getAllScoresJoueurs ();

    // pour récupérer les coordonnées des joueurs dans un certain secteur
    Map<String, Coordonnees> getJoueursAProximite();

    // Récupérer les messages par date (si date est vide, alors on récupère tout)
    Collection<MessageCMJ> getNewMessagesCMJ(LocalDate date);

    // Récupérer tous les Qr Codes
    Collection<QrCode> getQrCodes();
    // Récupérer tous les points pour la géolocalisation de la carte
    Collection<GeolocalisationVille> pointsAVisiter();
    // Récupérer tous les badges existants
    Collection<Badge> getAllBadges();

    // Récupérer périodiquement les réponses à un défi
    Collection<ReponseDefi> getReponsesDefi(LocalDate date);

    // Créer une question pour un défi
    void setQuestionDefi (int idDefi, QuestionDefi question);

    // Récupérer toutes les questions d'un défi
    Collection<QuestionDefi> getDefi (int idDefi);

    // Récupérer l'image d'un Jeu
    String imageJeu (int idJeu);




}
