package com.dill.api_rest.service;

import com.dill.api_rest.modele.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface Service {

    // pour charger les informations d'un joueur
    Joueur getJoueurByPseudo (String pseudo);

    // pour récupérer les scores
    Map<String, Collection<Score>> getAllScoresJoueurs ();

    // pour récupérer les coordonnées des joueurs dans un certain secteur
    Map<String, Coordonnees> getJoueursAProximite();

    // Récupérer les messages par date (si date est vide, alors on récupère tout)
    Collection<MessageCMJ> getNewMessagesCMJ(LocalDate date);

    // Récupérer tous les Qr Codes
    Collection<QrCode> getQrCodes();
    // Récupérer tous les points pour la géolocalisation de la carte
    Collection<GeolocalisationVille> getPointsAVisiter();
    // Récupérer tous les badges existants
    Collection<Badge> getAllBadges();

    // Récupérer périodiquement les réponses à un défi
    Collection<ReponseDefi> getReponsesDefi(LocalDate date);

    // Créer une question pour un défi
    void setQuestionDefi (int idDefi, QuestionDefi question);

    // Récupérer toutes les questions d'un défi
    Collection<QuestionDefi> getDefi (int idDefi);

    // Récupérer les images d'un Jeu
    Map<Integer, String> getImagesJeu(int idJeu);

    // Récupérer le logo d'un Jeu
    String getLogoJeu(int idJeu);

    // Créer un joueur
    void newJoueur(String pseudo);

    // Changer un score
    void modifierScore (String pseudo, int idJeu, int scoreJeu);

    // Changer un timer de jeu
    void modifierTemps (String pseudo, int idJeu, int tempsJeu);

    // charger des coordonnées
    void modifierCoords(String pseudo, double longitude, double latitude);

    // Supprimer un joueur
    void deleteJoueur (String pseudo);

    // Créer un badge ou le modifier
    void newBadge (int idBadge, String nomBadge, String imageBadge);

    // Supprimer un badge
    void deleteBadge (int idBadge);

    // Ajouter une nouvelle Géolocalisation
    void newGeolocalisation (Coordonnees newPoint, String nomPoint);

    // modifier une géolocalisation
    void modifierGeolocalisation (int idGeolocalisation, Coordonnees newPoint, String nomPoint);

    // Supprimer une Géolocalisation
    void deleteGeolocalisation (int idGeolocalisation);

    // Ajouter un Jeu
    void newJeu (String nom, String logo);

    // modifier logo Jeu
    void modifierLogoJeu (int idJeu, String logo);

    // Ajouter image Jeu ou les modifier
    void addImage(int idJeu, int numImage, String image);

    // Supprimer Jeu
    void deleteJeu (int idJeu);

    // Nouveau Message CMJ
    void newMessage (String message, String pseudo);

    // Supprimer Message
    void deleteMessage (int idMessage);

    // Nouveau défi (renvoie son id)
    void newDefi ();

    // supprimer défi
    void deleteDefi (int idDefi);

    // Ajouter une question au défi ou en modifier une
    void addQuestion(int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion);

    // Supprimer une question du défi (a voir si ca fonctionne avec seulement "idQuestion")
    void deleteQuestion (int idDefi, int idQuestion);

    // stocker les réponses des utilisateurs aux défis (utile pour manipuler les photos & les réponses textuelles non traitables directement par l'app)
    void addReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse);

}
