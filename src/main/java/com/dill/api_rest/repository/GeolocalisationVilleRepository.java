package com.dill.api_rest.repository;

import com.dill.api_rest.modele.Defi;
import com.dill.api_rest.modele.GeolocalisationVille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocalisationVilleRepository extends JpaRepository<GeolocalisationVille, Integer> {
}
