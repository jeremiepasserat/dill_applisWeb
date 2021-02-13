package com.dill.api_rest.service;

import com.dill.api_rest.modele.*;
import com.dill.api_rest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@Service
@Component
public class ServicesImplRepository implements Services {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    DefiRepository defiRepository;

    @Autowired
    GeolocalisationVilleRepository geolocalisationVilleRepository;

    @Autowired
    JeuRepository jeuRepository;

    @Autowired
    MessageCMJRepository messageCMJRepository;

    @Autowired
    QrCodeRepository qrCodeRepository;

    @Autowired
    ReponseDefiRepository reponseDefiRepository;

    /* Via JpaRepository

        findById(), findAll()

        save(), saveAll()

        exists(), existsById()

        delete(), deleteById(), deleteAll()

     */



    @Override
    public Joueur getJoueurByPseudo(String pseudo) {
        return null;
    }

    @Override
    public Map<String, Collection<Score>> getAllScoresJoueurs() {
        return null;
    }

    @Override
    public Map<String, Coordonnees> getJoueursAProximite() {
        return null;
    }

    @Override
    public Collection<MessageCMJ> getNewMessagesCMJ(LocalDate date) {
        return null;
    }

    @Override
    public Collection<QrCode> getQrCodes() {
        return null;
    }

    @Override
    public Collection<GeolocalisationVille> getPointsAVisiter() {
        return null;
    }

    @Override
    public Collection<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    @Override
    public Collection<ReponseDefi> getReponsesDefi(LocalDate date) {
        return null;
    }

    @Override
    public Collection<QuestionDefi> getDefi(int idDefi) {
        return null;
    }

    @Override
    public Map<Integer, String> getImagesJeu(int idJeu) {
        return null;
    }

    @Override
    public String getLogoJeu(int idJeu) {
        return null;
    }

    @Override
    public void newJoueur(String pseudo) {

    }

    @Override
    public void modifierScore(String pseudo, int idJeu, int scoreJeu) {

    }

    @Override
    public void modifierTemps(String pseudo, int idJeu, int tempsJeu) {

    }

    @Override
    public void modifierCoords(String pseudo, double longitude, double latitude) {

    }

    @Override
    public void ajouterPointVisite(String pseudo, int idPointVisite) {

    }

    @Override
    public void deleteJoueur(String pseudo) {

    }

    @Override
    @Transactional
    public void newBadge(int idBadge, String nomBadge, String imageBadge) {

        Badge badge = new Badge(idBadge, nomBadge, imageBadge);
        badgeRepository.save(badge);

    }

    @Override
    public void deleteBadge(int idBadge) {

    }

    @Override
    public void newGeolocalisation(Coordonnees newPoint, String nomPoint) {

    }

    @Override
    public void modifierGeolocalisation(int idGeolocalisation, Coordonnees newPoint, String nomPoint) {

    }

    @Override
    public void deleteGeolocalisation(int idGeolocalisation) {

    }

    @Override
    public void newJeu(String nom, String logo) {

    }

    @Override
    public void modifierLogoJeu(int idJeu, String logo) {

    }

    @Override
    public void purgeImagesJeu(int idJeu) {

    }

    @Override
    public void addImage(int idJeu, int numImage, String image) {

    }

    @Override
    public void deleteJeu(int idJeu) {

    }

    @Override
    public void newMessage(String message, String pseudo) {

    }

    @Override
    public void deleteMessage(int idMessage) {

    }

    @Override
    public void newDefi() {

    }

    @Override
    public void deleteDefi(int idDefi) {

    }

    @Override
    public void addQuestion(int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion) {

    }

    @Override
    public void deleteQuestion(int idDefi, int idQuestion) {

    }

    @Override
    public void addReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse) {

    }

    @Override
    public void deleteQrCode(int idQrCode) {

    }

    @Override
    public void newQrCode(String code, int scoreCode) {

    }

    @Override
    public boolean login(String pseudo, String password) {
        return false;
    }

    @Override
    public void validerCGU(String pseudo, LocalDate date) {

    }
}
