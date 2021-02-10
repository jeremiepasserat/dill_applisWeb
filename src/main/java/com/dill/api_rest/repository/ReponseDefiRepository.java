package com.dill.api_rest.repository;

import com.dill.api_rest.modele.ReponseDefi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseDefiRepository extends JpaRepository<ReponseDefi, Integer> {
}
