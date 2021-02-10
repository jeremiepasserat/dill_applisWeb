package com.dill.api_rest.repository;

import com.dill.api_rest.modele.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
}
