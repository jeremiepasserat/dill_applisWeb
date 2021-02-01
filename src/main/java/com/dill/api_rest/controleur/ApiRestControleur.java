package com.dill.api_rest.controleur;

import com.dill.api_rest.modele.Coordonnees;
import com.dill.api_rest.modele.Joueur;
import com.dill.api_rest.modele.Score;
import com.dill.api_rest.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiRestControleur {

    private static Service service;

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
    // getqrcodes
    // getmessagesbyDate
    // getQrCode
    // getPointsaVisiter
    // getAllBadges
    // getReponsesDefiByDate
    // setQuestionDefi
    // getDefi
    // getImagesJeu
    // getLogoJeu
    // newJoueur
    // modifierScore
    // modifierTemps
    // modifierCoords
    // deleteJoueur
    // newBadge
    // deleteBadge
    // newGeolocalisation
    // modifierGeolocalisation
    // deleteGeolocalisation
    // newJeu
    // modifierLogoJeu
    // addImage
    // deleteJeu
    // newMessage
    // deleteMessage
    // newDefi
    // deleteDefi
    // ajouterQuestion
    // supprimerQuestion
    // ajouterReponseDefi


    //@PostMapping, @GetMapping, @PatchMapping
    //ResponseEntity
    //@PathVariable, @RequestBody
}
