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

public class ServiceImpl implements Service {

    private BadgeDao badgeDao;
    private DefiDao defiDao;
    private GeolocalisationVilleDao geolocalisationVilleDao;
    private JeuDao jeuDao;
    private JoueurDao joueurDao;
    private MessageCMJDao messageCMJDao;
    private QrCodeDao qrCodeDao;
    private ReponseDefiDao reponseDefiDao;

    public ServiceImpl(BadgeDao badgeDao, DefiDao defiDao, GeolocalisationVilleDao geolocalisationVilleDao, JeuDao jeuDao, JoueurDao joueurDao, MessageCMJDao messageCMJDao, QrCodeDao qrCodeDao, ReponseDefiDao reponseDefiDao) {
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
    public Collection<GeolocalisationVille> getPointsAVisiter() {
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
    public Defi getDefi(int idDefi) {
        Defi defi = defiDao.find(idDefi);
        return defi.getQuestions();
    }

    @Override
    public Map<Integer, String> getImagesJeu(int idJeu) {
        Jeu jeu = jeuDao.find(idJeu);
        return jeu.getImagesJeu();
    }

    @Override
    public String getLogoJeu(int idJeu) {
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
    public void modifierCoords(String pseudo, double longitude, double latitude) {
        Joueur joueur = joueurDao.find(pseudo);
        joueur.setCoordonneesJoueur(new Coordonnees(longitude, latitude));
    }

    @Override
    @Transactional
    public void deleteJoueur(String pseudo) {
        joueurDao.remove(joueurDao.find(pseudo));
    }

    @Override
    @Transactional
    public void newBadge(int idBadge, String nomBadge, String imageBadge) {
        badgeDao.create(new Badge(idBadge, nomBadge, imageBadge));
    }

    @Override
    @Transactional
    public void deleteBadge(int idBadge) {
        badgeDao.remove(badgeDao.find(idBadge));
    }

    @Override
    @Transactional
    public void newGeolocalisation(Coordonnees newPoint, String nomPoint) {
        geolocalisationVilleDao.create(new GeolocalisationVille(newPoint, nomPoint));
    }

    @Override
    @Transactional
    public void modifierGeolocalisation(int idGeolocalisation, Coordonnees newPoint, String nomPoint) {

        GeolocalisationVille geolocalisationVille = geolocalisationVilleDao.find(idGeolocalisation);

        if (newPoint != null)
            geolocalisationVille.setCoordonneesPoint(newPoint);
        if (!nomPoint.equals(""))
            geolocalisationVille.setNomPoint(nomPoint);

        geolocalisationVilleDao.edit(geolocalisationVille);
    }

    @Override
    @Transactional
    public void deleteGeolocalisation(int idGeolocalisation) {
        geolocalisationVilleDao.remove(geolocalisationVilleDao.find(idGeolocalisation));
    }

    @Override
    @Transactional
    public void newJeu(String nom, String logo) {
        jeuDao.create(new Jeu(nom, logo));
    }

    @Override
    @Transactional
    public void modifierLogoJeu(int idJeu, String logo) {
        Jeu jeu = jeuDao.find(idJeu);
        jeu.setLogoJeu(logo);
        jeuDao.edit(jeu);

    }

    @Override
    @Transactional
    public void purgeImagesJeu(int idJeu) {
        Jeu jeu = jeuDao.find(idJeu);
        jeu.purgeImages();
        jeuDao.edit(jeu);
    }

    @Override
    @Transactional
    public void addImage(int idJeu, int numImage, String image) {
        Jeu jeu = jeuDao.find(idJeu);
        jeu.addImage(numImage, image);
        jeuDao.edit(jeu);
    }

    @Override
    @Transactional
    public void deleteJeu(int idJeu) {
        jeuDao.remove(jeuDao.find(idJeu));
    }

    @Override
    @Transactional
    public void newMessage(String message, String pseudo) {
        messageCMJDao.create(new MessageCMJ(message, joueurDao.find(pseudo)));
    }

    @Override
    @Transactional
    public void deleteMessage(int idMessage) {
        messageCMJDao.remove(messageCMJDao.find(idMessage));
    }

    @Override
    @Transactional
    public void newDefi() {
        defiDao.create(new Defi());
    }

    @Override
    public void deleteDefi(int idDefi) {
        defiDao.remove(defiDao.find(idDefi));
    }

    @Override
    @Transactional
    public void addQuestion(int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion) {
        QuestionDefi questionDefi = new QuestionDefi(numQuestion, texteQuestion, imageQuestion, reponseQuestion);
        Defi defi = defiDao.find(idDefi);
        defi.ajouterQuestion(questionDefi);
        defiDao.edit(defi);
    }

    @Override
    @Transactional
    public void deleteQuestion(int idDefi, int idQuestion) {
        Defi defi = defiDao.find(idDefi);
        defi.supprimerQuestion(idQuestion);
    }


    @Override
    @Transactional
    public void addReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse) {
        ReponseDefi reponseDefi = new ReponseDefi(idDefi, pseudo, numQuestion, texteReponse, imageReponse);
        reponseDefiDao.create(reponseDefi);
    }
}
