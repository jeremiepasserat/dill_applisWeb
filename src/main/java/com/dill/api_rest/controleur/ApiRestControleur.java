package com.dill.api_rest.controleur;

import com.dill.api_rest.modele.*;
import com.dill.api_rest.service.Services;
import com.dill.api_rest.service.ServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiRestControleur {

    //Services service = new ServicesImpl();

    @Autowired
    Services service;

  /*  @GetMapping("/test")
    ResponseEntity<String> test(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String upd = "update authorities set username = \"saucisse\" where authority = \"ROLE_ADMIN\"";




        String upf = "insert into users values (\"fromage\", \"" + passwordEncoder.encode("fromage") + "\", 1)";
        System.out.println(upd);

        String upe = "insert into authorities values (\"fromage\", \"ROLE_ADMIN\")";
        System.out.println(upd);

        jdbcTemplate.execute("delete from authorities");
        jdbcTemplate.execute("delete from users");

        jdbcTemplate.execute(upf);
        jdbcTemplate.execute(upe);
        return ResponseEntity.ok("test");
    }*/

    // Se connecter
    @PostMapping("/login")
    ResponseEntity<Void>login(@RequestBody String pseudo, @RequestBody String mdp){
        if (service.login(pseudo, mdp)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Valider les cgu
    @PostMapping("/cgu")
    ResponseEntity<Void>validerCgu(@RequestBody String pseudo, @RequestBody LocalDate date){
        service.validerCGU(pseudo, date);
        return ResponseEntity.ok().build();
    }

    // getjoueurbypseudo
    @GetMapping("/joueur/{pseudo}")
    ResponseEntity<Joueur> getJoueurByPseudo (@PathVariable String pseudo){
        Joueur joueur = service.getJoueurByPseudo(pseudo);
        if (joueur != null)
        return ResponseEntity.ok().body(service.getJoueurByPseudo(pseudo));
        else
            return ResponseEntity.notFound().build();
    }

    // getallscores
    @GetMapping("/allScores")
    ResponseEntity<Map<String, Collection<Score>>> getAllScores (){
        return ResponseEntity.ok().body(service.getAllScoresJoueurs());
    }

    // getjoueursaproximite
    @GetMapping("/aProximite")
    ResponseEntity<Map<String, Coordonnees>> aProximite(){
        return ResponseEntity.ok().body(service.getJoueursAProximite());
    }

    // getnewmessages
    @GetMapping("/messagesByDate")
    ResponseEntity<Collection<MessageCMJ>> messagesParDate(@RequestBody LocalDate date){
        return ResponseEntity.ok().body(service.getNewMessagesCMJ(date));
    }

    // getqrcodes
    @GetMapping("/qrCodes")
    ResponseEntity<Collection<QrCode>> allCodes(){
        return ResponseEntity.ok().body(service.getQrCodes());
    }

    // getPointsaVisiter
    @GetMapping("/pointsGeo")
    ResponseEntity<Collection<GeolocalisationVille>> allPointsGeolocalises(){
        return ResponseEntity.ok().body(service.getPointsAVisiter());
    }


    // getAllBadges
    @GetMapping("/badges")
    ResponseEntity<Collection<Badge>> allBadges(){
        return ResponseEntity.ok().body(service.getAllBadges());
    }

    // getReponsesDefiByDate
    @GetMapping("/reponsesDefi")
    ResponseEntity<Collection<ReponseDefi>> reponsesDefiByDate(@RequestBody LocalDate date){
        return ResponseEntity.ok().body(service.getReponsesDefi(date));
    }

    // getDefi
    @GetMapping("/defi")
    ResponseEntity<Collection<QuestionDefi>> getDefi(int idDefi){
        return ResponseEntity.ok().body(service.getDefi(idDefi));
    }

    // getImagesJeu
    @GetMapping("/jeu/{idJeu}/images")
    ResponseEntity<Map<Integer, String>> getImagesJeu (@PathVariable int idJeu){
        return ResponseEntity.ok().body(service.getImagesJeu(idJeu));
    }

    // getLogoJeu
    @GetMapping("/jeu/{idJeu}/logo")
    ResponseEntity<String> getLogoJeu (@PathVariable int idJeu){
        return ResponseEntity.ok().body(service.getLogoJeu(idJeu));
    }

    // newJoueur
    @PostMapping("/newJoueur")
    ResponseEntity<String> createJoueur(@RequestBody String pseudo){

        service.newJoueur(pseudo);
        return ResponseEntity.ok().body("Joueur créé");
    }

    // modifierScore
    @PatchMapping("/modifierScore")
    ResponseEntity<String> modifierScore(@RequestBody String pseudo, @RequestBody int idJeu, @RequestBody int scoreJeu){

        service.modifierScore(pseudo, idJeu, scoreJeu);
        return ResponseEntity.ok("Score modifié");
    }


    // modifierTemps
    @PatchMapping("/modifierTemps")
    ResponseEntity<String> modifierTemps(@RequestBody String pseudo, @RequestBody int idJeu, @RequestBody int tempsJeu){

        service.modifierTemps(pseudo, idJeu, tempsJeu);
        return ResponseEntity.ok("Temps modifié");
    }

    // modifierCoords
    @PatchMapping("/modifierCoords")
    ResponseEntity<String> modifierCoords(@RequestBody String pseudo, @RequestBody double longitude, @RequestBody double latitude){

        service.modifierCoords(pseudo, longitude, latitude);
        return ResponseEntity.ok("Coordonnées du joueur modifiées");
    }

    // ajouter un point visite
    @PatchMapping("/ajouterPointVisite")
    ResponseEntity<String> ajouterPointVisite(@RequestBody String pseudo, @RequestBody int idPointVisite){

        service.ajouterPointVisite(pseudo, idPointVisite);
        return ResponseEntity.ok("point visité ajouté");
    }

    // deleteJoueur
    @DeleteMapping("/deleteJoueur/{pseudo}")
    ResponseEntity<String> deleteJoueur(@PathVariable String pseudo){

        service.deleteJoueur(pseudo);
        return ResponseEntity.ok("Joueur Supprimé");
    }


    // newBadge
    @PostMapping("/newbadge")
    ResponseEntity<String> createBadge(@RequestBody Badge badge){

        service.newBadge(badge.getId(), badge.getNom(), badge.getImage());
        return ResponseEntity.ok().body("Badge créé");
    }

    // deleteBadge
    @DeleteMapping("/deleteBadge/{idBadge}")
    ResponseEntity<String> deleteBadge(@PathVariable int idBadge){

        service.deleteBadge(idBadge);
        return ResponseEntity.ok("Badge Supprimé");
    }

    // newQrCode
    @PostMapping("/newQrCode")
    ResponseEntity<String> createQrCode(@RequestBody QrCode code){

        service.newQrCode(code.getTexteCode(), code.getScoreCode());
        return ResponseEntity.ok().body("Badge créé");
    }

    // deleteQrCode
    @DeleteMapping("/deleteQrCode/{idQrCode}")
    ResponseEntity<String> deleteQrCode(@PathVariable int idQrCode){

        service.deleteQrCode(idQrCode);
        return ResponseEntity.ok("Qr Code Supprimé");
    }


    // newGeolocalisation
    @PostMapping("/newPointGeo")
    ResponseEntity<String> createGeolocalisation(@RequestBody GeolocalisationVille geolocalisationVille){

        service.newGeolocalisation(geolocalisationVille.getCoordonneesPoint(), geolocalisationVille.getNomPoint());
        return ResponseEntity.ok().body("Nouveau Point de Géolocalisation créé");
    }

    // modifierGeolocalisation
    @PatchMapping("/modifierGeoloc")
    ResponseEntity<String> modifierGeolocalisation(@RequestBody int idPointGeo, @RequestBody Coordonnees newPoint, @RequestBody String nomPoint){

        service.modifierGeolocalisation(idPointGeo, newPoint, nomPoint);
        return ResponseEntity.ok("Coordonnées du Point Géolocalisé modifiées");
    }

    // deleteGeolocalisation
    @DeleteMapping("/deletePointGeo/{pointGeo}")
    ResponseEntity<String> deletePointGeo(@PathVariable int pointGeo){

        service.deleteGeolocalisation(pointGeo);
        return ResponseEntity.ok("Point de géolocalisation Supprimé");
    }

    // newJeu
    @PostMapping("/newJeu")
    ResponseEntity<String> createJeu(@RequestBody Jeu jeu){

        service.newJeu(jeu.getNomJeu(), jeu.getLogoJeu());
        return ResponseEntity.ok().body("Jeu créé" );
    }

    // modifierLogoJeu
    @PatchMapping("/modifierLogoJeu/{idJeu}")
    ResponseEntity<String> modifierLogoJeu(@PathVariable int idJeu, @RequestBody String newLogo){

        service.modifierLogoJeu(idJeu, newLogo);
        return ResponseEntity.ok("Logo jeu modifié");
    }

    // Nettoyer toutes les images d'un jeu
    @PatchMapping("/purgeImagesJeu/{idJeu}")
    ResponseEntity<String> purgeImagesJeu(@PathVariable int idJeu){

        service.purgeImagesJeu(idJeu);
        return ResponseEntity.ok("Images jeu purgées");
    }

    // addImage
    @PostMapping("/newImageJeu/{idJeu}")
    ResponseEntity<String> addImageJeu(@PathVariable int idJeu, @RequestBody int idImage, @RequestBody String image){

        service.addImage(idJeu, idImage, image);
        return ResponseEntity.ok("Image insérée");

    }

    // deleteJeu
    @DeleteMapping("/deleteJeu/{idJeu}")
    ResponseEntity<String> deleteJeu(@PathVariable int idJeu){

        service.deleteJeu(idJeu);
        return ResponseEntity.ok("Jeu Supprimé");
    }

    // newMessage
    @PostMapping("/newMessage")
    ResponseEntity<String> createMessage(@RequestBody MessageCMJ messageCMJ){

        service.newMessage(messageCMJ.getMessage(), messageCMJ.getPseudoJoueur());
        return ResponseEntity.ok().body("Message créé");
    }

    // deleteMessage
    @DeleteMapping("/deleteMessage/{idMessage}")
    ResponseEntity<String> deleteMessage(@PathVariable int idMessage){
        service.deleteMessage(idMessage);
        return ResponseEntity.ok("Message Supprimé");
    }

    // newDefi
    @PostMapping("/newDefi")
    ResponseEntity<String> createDefi(){
        service.newDefi();
        return ResponseEntity.ok().body("Défi créé");
    }

    // deleteDefi
    @DeleteMapping("/deleteDefi/{idDefi}")
    ResponseEntity<String> deleteDefi(@PathVariable int idDefi){

        service.deleteDefi(idDefi);
        return ResponseEntity.ok("Joueur Supprimé");
    }


    // ajouterQuestion
    @PostMapping("/addQuestion/{idDefi}")
    ResponseEntity<String> addQuestionDefi(@PathVariable int idDefi, @RequestBody QuestionDefi questionDefi){

        service.addQuestion(idDefi, questionDefi.getNumQuestion(), questionDefi.getTexteQuestion(), questionDefi.getImageQuestion(), questionDefi.getReponseQuestion());
        return ResponseEntity.ok("Question créée");

    }

    // supprimerQuestion
    @DeleteMapping("/deleteQuestion/{idDefi}/{numQuestion}")
    ResponseEntity<String> deleteQuestion(@PathVariable int idDefi, @PathVariable int numQuestion){

        service.deleteQuestion(idDefi, numQuestion);
        return ResponseEntity.ok("Question Supprimé");
    }

    // ajouterReponseDefi
    @PostMapping("/reponseDefi/{idDefi}")
    ResponseEntity<String> addReponseDefi(@PathVariable int idDefi, @RequestBody ReponseDefi reponseDefi){

        service.addReponseDefi(idDefi, reponseDefi.getPseudoJoueur(), reponseDefi.getNumQuestion(), reponseDefi.getTexteReponse(), reponseDefi.getImageReponse());
        return ResponseEntity.ok("Réponse au défi recue");

    }

}
