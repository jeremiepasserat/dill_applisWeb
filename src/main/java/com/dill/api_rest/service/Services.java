package com.dill.api_rest.service;

import com.dill.api_rest.modele.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface Services {

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
    Collection<GeolocalisationVille> pointsAVisiter();
    // Récupérer tous les badges existants
    Collection<Badge> getAllBadges();

    // Récupérer périodiquement les réponses à un défi
    Collection<ReponseDefi> getReponsesDefi(LocalDate date);

    // Créer une question pour un défi
    void setQuestionDefi (int idDefi, QuestionDefi question);

    // Récupérer toutes les questions d'un défi
    Collection<QuestionDefi> getDefi (int idDefi);

    // Récupérer les images d'un Jeu
    Map<Integer, String> imageJeu (int idJeu);

    // Récupérer le logo d'un Jeu
    String logoJeu (int idJeu);

    // Créer un joueur (renvoie son id)
    void newJoueur(String pseudo);

    // Changer un score
    void modifierScore (String pseudo, int idJeu, int scoreJeu);

    // Changer un timer de jeu
    void modifierTemps (String pseudo, int idJeu, int tempsJeu);

    // charger des coordonnées
    void changeCoords (String pseudo, double longitude, double latitude);

    // Supprimer un joueur
    void deleteJoueur (String pseudo);

    // Créer un badge ou le modifier
    Badge newBadge (int idBadge, String nomBadge, String imageBadge);

    // Supprimer un badge
    void deleteBadge (int idBadge);

    // Ajouter une nouvelle Géolocalisation
    GeolocalisationVille newGeolocalisation (Coordonnees newPoint, String nomPoint);

    // modifier une géolocalisation
    GeolocalisationVille modifierGeolocalisation (int idGeolocalisation, Coordonnees newPoint, String nomPoint);

    // Supprimer une Géolocalisation
    void deleteGeolocalisation (int idGeolocalisation);

    // Ajouter un Jeu (renvoir l'id)
    int newJeu (String nom, String logo);

    // modifier logo Jeu
    void modifierLogoJeu (int idJeu, String logo);

    // Ajouter image Jeu ou les modifier
    int AddImage (int idJeu, int numImage, String image);

    // Supprimer Jeu
    void deleteJeu (int idJeu);

    // Nouveau Message CMJ
    MessageCMJ newMessage (String message, String pseudo);

    // Supprimer Message
    void deleteMessage (int idMessage);

    // Nouveau défi (renvoie son id)
    int newDefi ();

    // Ajouter une question au défi (renvoie le numéro de question) ou en modifier une
    int ajouterQuestion (int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion);

    // Supprimer une question du défi (a voir si ca fonctionne avec seulement "idQuestion")
    void supprimerQuestion (int idDefi, int idQuestion);

    // Supprimer un défi
    void deleteQuestion (int idDefi);

    void ajouterReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse);

}
