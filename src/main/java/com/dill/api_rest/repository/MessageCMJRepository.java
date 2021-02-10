package com.dill.api_rest.repository;

import com.dill.api_rest.modele.MessageCMJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageCMJRepository extends JpaRepository<MessageCMJ, Integer> {
}
