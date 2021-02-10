package com.dill.api_rest.repository;

import com.dill.api_rest.modele.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
}
