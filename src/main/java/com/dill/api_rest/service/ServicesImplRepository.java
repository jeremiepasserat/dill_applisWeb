package com.dill.api_rest.service;

import com.dill.api_rest.modele.*;
import com.dill.api_rest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Component
public class ServicesImplRepository implements Services {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    DefiRepository defiRepository;

    @Autowired
    GeolocalisationVilleRepository geolocalisationVilleRepository;

    @Autowired
    JoueurRepository joueurRepository;

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
    public Optional<Joueur> getJoueurByPseudo(String pseudo) {
        return joueurRepository.findById(pseudo);
    }

    @Override
    public Map<String, Collection<Score>> getAllScoresJoueurs() {

        Collection<Joueur> joueurs = joueurRepository.findAll();

        return joueurs.stream().collect(Collectors.toMap(Joueur::getPseudo, Joueur::getScoresJeu));
    }

    @Override
    public Map<String, Coordonnees> getJoueursAProximite() {

        Collection<Joueur> joueurs = joueurRepository.findAll();

        return joueurs.stream().collect(Collectors.toMap(Joueur::getPseudo, Joueur::getCoordonneesJoueur));
    }

    @Override
    public Collection<MessageCMJ> getNewMessagesCMJ(LocalDate date) {
        return messageCMJRepository.findAll().stream().filter(messageCMJ -> messageCMJ.getDateMessage() == date).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<QrCode> getQrCodes() {
        return qrCodeRepository.findAll();
    }

    @Override
    public Collection<GeolocalisationVille> getPointsAVisiter() {
        return geolocalisationVilleRepository.findAll();
    }

    @Override
    public Collection<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    @Override
    public Collection<ReponseDefi> getReponsesDefi(LocalDate date) {
        return reponseDefiRepository.findAll();
    }

    @Override
    public Collection<QuestionDefi> getDefi(int idDefi) {
        return defiRepository.findById(idDefi).orElseThrow().getQuestions();
    }

    @Override
    public Map<Integer, String> getImagesJeu(int idJeu) {
        return jeuRepository.findById(idJeu).orElseThrow().getImagesJeu();
    }

    @Override
    public String getLogoJeu(int idJeu) {
        return jeuRepository.findById(idJeu).orElseThrow().getLogoJeu();
    }

    @Override
    @Transactional
    public void newJoueur(String pseudo, String password, LocalDate acceptationCGU, Parent parent) {
       joueurRepository.save(new Joueur(pseudo, password, acceptationCGU, parent));
    }

    @Override
    @Transactional
    public void modifierScore(String pseudo, int moisJeu, int idJeu, int scoreJeu, int tempsJeu) {
        Joueur joueur = joueurRepository.findById(pseudo).orElseThrow();
        joueur.modifierScore(moisJeu, idJeu, scoreJeu, tempsJeu);
        joueurRepository.save(joueur);
    }


    @Override
    @Transactional
    public void modifierCoords(String pseudo, double longitude, double latitude) {

        Joueur joueur = joueurRepository.findById(pseudo).orElseThrow();
        joueur.setCoordonneesJoueur(new Coordonnees(longitude, latitude));
        joueurRepository.save(joueur);
    }

    @Override
    @Transactional
    public void ajouterPointVisite(String pseudo, int idPointVisite) {
        Joueur joueur = joueurRepository.findById(pseudo).orElseThrow();
        joueur.ajouterPointVisite(geolocalisationVilleRepository.findById(idPointVisite).orElseThrow());
        joueurRepository.save(joueur);
    }

    @Override
    @Transactional
    public void deleteJoueur(String pseudo) {
        joueurRepository.deleteById(pseudo);
    }

    @Override
    @Transactional
    public void newBadge(int idBadge, String nomBadge, String imageBadge) {

        Badge badge = new Badge(idBadge, nomBadge, imageBadge);
        badgeRepository.save(badge);

    }

    @Override
    @Transactional
    public void deleteBadge(int idBadge) {
        badgeRepository.deleteById(idBadge);
    }

    @Override
    @Transactional
    public void newGeolocalisation(Coordonnees newPoint, String nomPoint) {
        geolocalisationVilleRepository.save(new GeolocalisationVille(newPoint, nomPoint));
    }

    @Override
    @Transactional
    public void modifierGeolocalisation(int idGeolocalisation, Coordonnees newPoint, String nomPoint) {
        GeolocalisationVille geolocalisationVille = geolocalisationVilleRepository.findById(idGeolocalisation).orElseThrow();
        geolocalisationVille.setNomPoint(nomPoint);
        geolocalisationVille.setCoordonneesPoint(newPoint);
        geolocalisationVilleRepository.save(geolocalisationVille);

    }

    @Override
    @Transactional
    public void deleteGeolocalisation(int idGeolocalisation) {
        geolocalisationVilleRepository.deleteById(idGeolocalisation);
    }

    @Override
    @Transactional
    public void newJeu(String nom, String logo) {
        jeuRepository.save(new Jeu(nom, logo));
    }

    @Override
    @Transactional
    public void modifierLogoJeu(int idJeu, String logo) {
        Jeu jeu = jeuRepository.findById(idJeu).orElseThrow();
        jeu.setLogoJeu(logo);
        jeuRepository.save(jeu);
    }

    @Override
    @Transactional
    public void purgeImagesJeu(int idJeu) {
        Jeu jeu = jeuRepository.findById(idJeu).orElseThrow();
        jeu.purgeImages();
        jeuRepository.save(jeu);
    }

    @Override
    @Transactional
    public void addImage(int idJeu, int numImage, String image) {
        Jeu jeu = jeuRepository.findById(idJeu).orElseThrow();
        jeu.addImage(numImage, image);
        jeuRepository.save(jeu);
    }

    @Override
    @Transactional
    public void deleteJeu(int idJeu) {
        jeuRepository.deleteById(idJeu);
    }

    @Override
    @Transactional
    public void newMessage(String message, String pseudo) {
        messageCMJRepository.save(new MessageCMJ(message, pseudo));
    }

    @Override
    @Transactional
    public void deleteMessage(int idMessage) {
        messageCMJRepository.deleteById(idMessage);
    }

    @Override
    @Transactional
    public void newDefi() {
        defiRepository.save(new Defi());
    }

    @Override
    @Transactional
    public void deleteDefi(int idDefi) {
        defiRepository.deleteById(idDefi);
    }

    @Override
    @Transactional
    public void addQuestion(int idDefi, int numQuestion, String texteQuestion, String imageQuestion, String reponseQuestion) {
        Defi defi = defiRepository.findById(idDefi).orElseThrow();
        defi.ajouterQuestion(new QuestionDefi(numQuestion, texteQuestion, imageQuestion, reponseQuestion));
        defiRepository.save(defi);
    }

    @Override
    @Transactional
    public void deleteQuestion(int idDefi, int idQuestion) {
        Defi defi = defiRepository.findById(idDefi).orElseThrow();
        defi.supprimerQuestion(idQuestion);
        defiRepository.save(defi);
    }

    @Override
    @Transactional
    public void addReponseDefi(int idDefi, String pseudo, int numQuestion, String texteReponse, String imageReponse) {
        reponseDefiRepository.save(new ReponseDefi(idDefi, pseudo, numQuestion, texteReponse, imageReponse));
    }

    @Override
    @Transactional
    public void deleteQrCode(int idQrCode) {
        qrCodeRepository.deleteById(idQrCode);
    }

    @Override
    @Transactional
    public void newQrCode(String code, int scoreCode) {
        qrCodeRepository.save(new QrCode(code, scoreCode));
    }

    @Override
    @Transactional
    public boolean login(String pseudo, String password) {

        Optional<Joueur> joueur = joueurRepository.findById(pseudo);
        return joueur.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    @Override
    @Transactional
    public void validerCGU(String pseudo, LocalDate date) {
        Joueur joueur = joueurRepository.findById(pseudo).orElseThrow();
        joueur.setAcceptationCGU(date);
        joueurRepository.save(joueur);
    }

    @Override
    @Transactional
    public void modifierBadge(int id, String nom, String image) {
        Badge badge = badgeRepository.findById(id).orElseThrow();
        badge.setNom(nom);
        badge.setImage(image);
        badgeRepository.save(badge);
    }
}
