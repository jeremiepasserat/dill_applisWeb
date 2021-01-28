package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.dao.QrCodeDao;
import com.dill.api_rest.modele.GeolocalisationVille;
import com.dill.api_rest.modele.QrCode;

public class QrCodeDaoImpl  extends AbstractDaoImpl<QrCode> implements QrCodeDao {
    public QrCodeDaoImpl(Class<QrCode> entityClass) {
        super(entityClass);
    }
}
