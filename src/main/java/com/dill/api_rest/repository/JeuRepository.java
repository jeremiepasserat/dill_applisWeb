package com.dill.api_rest.repository;

import com.dill.api_rest.modele.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuRepository extends JpaRepository<Jeu, Integer> {
}
