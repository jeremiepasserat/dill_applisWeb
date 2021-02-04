package com.dill.api_rest.controleur;

import com.dill.api_rest.modele.*;
import com.dill.api_rest.service.Service;
import org.apache.catalina.realm.GenericPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiRestControleur {

    private static Service service;


    @GetMapping("/test")
    ResponseEntity<String> test
            (){
        return ResponseEntity.ok("test");
    }

    @GetMapping("/username")
    ResponseEntity<String> user (Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

        return ResponseEntity.ok("test : " + principal.getName() + "-" + hasUserRole);
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
    ResponseEntity<Collection<MessageCMJ>> aProximite(@RequestBody LocalDate date){
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
    ResponseEntity<Collection<QrCode>> allBadges(){
        return ResponseEntity.ok().body(service.getQrCodes());
    }

    // getReponsesDefiByDate
    @GetMapping("/reponsesDefi")
    ResponseEntity<Collection<ReponseDefi>> reponsesDefiByDate(@RequestBody LocalDate date){
        return ResponseEntity.ok().body(service.getReponsesDefi(date));
    }

    // getDefi
    @GetMapping("defi")
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
    @DeleteMapping("/deleteJoueur/{idBadge}")
    ResponseEntity<String> deleteBadge(@PathVariable int idBadge){

        service.deleteBadge(idBadge);
        return ResponseEntity.ok("Badge Supprimé");
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
    @DeleteMapping("/deleteJeu/{pointGeo}")
    ResponseEntity<String> deletePointGeo(@PathVariable int pointGeo){

        service.deleteGeolocalisation(pointGeo);
        return ResponseEntity.ok("Point de géolocalisation Supprimé");
    }

    // newJeu
    @PostMapping("/newJeu")
    ResponseEntity<String> createJeu(@RequestBody Jeu jeu){
        service.newJeu(jeu.getNomJeu(), jeu.getLogoJeu());
        return ResponseEntity.ok().body("Jeu créé");
    }

    // modifierLogoJeu
    @PatchMapping("/modifierLogoJeu/{idJeu}")
    ResponseEntity<String> modifierLogoJeu(@PathVariable int idJeu, @RequestBody String newLogo){
        service.modifierLogoJeu(idJeu, newLogo);
        return ResponseEntity.ok("Logo jeu modifié");
    }

    // purgeImages
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

    // Nettoyer toutes les images d'un jeu
    @DeleteMapping("/purgerImagesJeu/{idJeu}")
    ResponseEntity<String> purgerImagesJeu(@PathVariable int idJeu){

        service.purgeImagesJeu(idJeu);
        return ResponseEntity.ok("Images jeu purgées");

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
        service.newMessage(messageCMJ.getMessage(), messageCMJ.getJoueur().getPseudo());
        return ResponseEntity.ok().body("Message créé");
    }

    // deleteMessage
    @DeleteMapping("/deleteJoueur/{idMessage}")
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
    @DeleteMapping("/deleteJoueur/{idDefi}")
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
    @DeleteMapping("/deleteJoueur/{idDefi}/{numQuestion}")
    ResponseEntity<String> deleteQuestion(@PathVariable int idDefi, @PathVariable int numQuestion){

        service.deleteQuestion(idDefi, numQuestion);
        return ResponseEntity.ok("Question Supprimé");
    }

    // ajouterReponseDefi
    @PostMapping("/newImageJeu/{idDefi}")
    ResponseEntity<String> addReponseDefi(@PathVariable int idDefi, @RequestBody String pseudo,  @RequestBody ReponseDefi reponseDefi){

        service.addReponseDefi(idDefi, pseudo, reponseDefi.getNumQuestion(), reponseDefi.getTexteReponse(), reponseDefi.getImageReponse());
        return ResponseEntity.ok("Réponse au défi recue");

    }

}
