package com.dill.api_rest.repository;

import com.dill.api_rest.modele.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, String> {
}
