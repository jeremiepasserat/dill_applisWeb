package com.dill.api_rest.service;

import com.dill.api_rest.dao.*;
import com.dill.api_rest.modele.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicesImpl implements Services {

    private BadgeDao badgeDao;
    private DefiDao defiDao;
    private GeolocalisationVilleDao geolocalisationVilleDao;
    private JeuDao jeuDao;
    private JoueurDao joueurDao;
    private MessageCMJDao messageCMJDao;
    private QrCodeDao qrCodeDao;
    private ReponseDefiDao reponseDefiDao;

    public ServicesImpl(BadgeDao badgeDao, DefiDao defiDao, GeolocalisationVilleDao geolocalisationVilleDao, JeuDao jeuDao, JoueurDao joueurDao, MessageCMJDao messageCMJDao, QrCodeDao qrCodeDao, ReponseDefiDao reponseDefiDao) {
        this.badgeDao = badgeDao;
        this.defiDao = defiDao;
        this.geolocalisationVilleDao = geolocalisationVilleDao;
        this.jeuDao = jeuDao;
        this.joueurDao = joueurDao;
        this.messageCMJDao = messageCMJDao;
        this.qrCodeDao = qrCodeDao;
        this.reponseDefiDao = reponseDefiDao;
    }

    @Override
    public Joueur getJoueurByPseudo(String pseudo) {
        return joueurDao.find(pseudo);
    }

    @Override
    public Map<String, Collection<Score>> getAllScoresJoueurs() {
        Collection<Joueur> joueurs = joueurDao.findAll();

        Map<String, Collection<Score>> scoresJoueurs = new HashMap<>();

        for (Joueur j : joueurs){

            scoresJoueurs.put(j.getPseudo(), j.getScoresJeu());
        }

        return scoresJoueurs;
    }

    // (Pour l'instant, renvoie les coordonnées de tous les joueurs (aucun filtre)
    @Override
    public Map<String, Coordonnees> getJoueursAProximite() {

        Collection<Joueur> joueurs = joueurDao.findAll();

        Map<String, Coordonnees> coordsJoueurs = new HashMap<>();

        for (Joueur j : joueurs){

            coordsJoueurs.put(j.getPseudo(), j.getCoordonneesJoueur());
        }

        return coordsJoueurs;
    }


    @Override
    public Collection<MessageCMJ> getNewMessagesCMJ(LocalDate date) {

        Collection<MessageCMJ> messageCMJS = messageCMJDao.findAll().stream().filter(messageCMJ -> messageCMJ.getDateMessage() == date).collect(Collectors.toCollection(ArrayList::new));

        return messageCMJS;
    }

    @Override
    public Collection<QrCode> getQrCodes() {
        return qrCodeDao.findAll();
    }

    @Override
    public Collection<GeolocalisationVille> pointsAVisiter() {
        return geolocalisationVilleDao.findAll();
    }

    @Override
    public Collection<Badge> getAllBadges() {
        return badgeDao.findAll();
    }

    @Override
    public Collection<ReponseDefi> getReponsesDefi(LocalDate date) {
        Collection<ReponseDefi> reponseDefis = reponseDefiDao.findAll().stream().filter(reponseDefi -> reponseDefi.getDateReponse() == date).collect(Collectors.toCollection(ArrayList::new));

        return reponseDefis;
    }

    @Override
    public void setQuestionDefi(int idDefi, QuestionDefi question) {

    }

    @Override
    public Collection<QuestionDefi> getDefi(int idDefi) {
        Defi defi = defiDao.find(idDefi);
        return defi.getQuestions();
    }

    @Override
    public Map<Integer, String> imageJeu(int idJeu) {
        Jeu jeu = jeuDao.find(idJeu);
        return jeu.getImagesJeu();
    }

    @Override
    public String logoJeu(int idJeu) {
        Jeu jeu = jeuDao.find(idJeu);
        return jeu.getLogoJeu();
    }

    @Override
    @Transactional
    public void newJoueur(String pseudo) {


        joueurDao.create(new Joueur(pseudo));

    }

    @Override
    @Transactional
    public void modifierScore(String pseudo, int idJeu, int scoreJeu) {
        Joueur joueur = joueurDao.find(pseudo);
        for (Score s : joueur.getScoresJeu()){
            if (s.getIdJeu() == idJeu){
                s.setScoreJeu(scoreJeu);
            }
        }
    }

    @Override
    @Transactional
    public void modifierTemps(String pseudo, int idJeu, int tempsJeu) {
        Joueur joueur = joueurDao.find(pseudo);
        for (Score s : joueur.getScoresJeu()){
            if (s.getIdJeu() == idJeu){
                s.setTempsJeu(tempsJeu);
            }
        }
    }

    @Override
    @Transactional
    public void changeCoords(String pseudo, double longitude, double latitude) {
        Joueur joueur = joueurDao.find(pseudo);
        joueur.setCoordonneesJoueur(new Coordonnees(longitude, latitude));
    }

    @Override
    public void deleteJoueur(String pseudo) {
        joueurDao.remove(joueurDao.find(pseudo));
    }

    @Override
    public Badge newBadge(int idBadge, String nomBadge, String imageBadge) {
        return null;
    }

    @Override
    public void deleteBadge(int idBadge) {

    }

    @Override
    public GeolocalisationVille newGeolocalisation(Coordonnees newPoint, String nomPoint) {
        return null;
    }

    @Override
    public GeolocalisationVille modifierGeolocalisation(int idGeolocalisation, Coordonnees newPoint, String nomPoint) {
        return null;
    }

    @Override
    public void deleteGeolocalisation(int idGeolocalisation) {

    }

    @Override
    public int newJeu(String nom, String logo) {
        return 0;
    }

    @Override
    public void modifierLogoJeu(int idJeu, String logo) {

    }

    @Override
    public int AddImage(int idJeu, int numImage, String image) {
        return 0;
    }

    @Override
    public void deleteJeu(int idJeu) {

    }

    @Override
    public MessageCMJ newMessage(String message, String pseudo) {
        return null;
    }

    @Override
    public void deleteMessage(int idMessage) {

    }

    @Override
    public int newDefi() {
        return 0;
    }

    @Override
    public int ajouterQuestion(int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion) {
        return 0;
    }

    @Override
    public void supprimerQuestion(int idDefi, int idQuestion) {

    }

    @Override
    public void deleteQuestion(int idDefi) {

    }

    @Override
    public void ajouterReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse) {

    }
}
