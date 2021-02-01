package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.dao.ReponseDefiDao;
import com.dill.api_rest.modele.GeolocalisationVille;
import com.dill.api_rest.modele.ReponseDefi;

public class ReponseDefiDaoImpl  extends AbstractDaoImpl<ReponseDefi> implements ReponseDefiDao {
    public ReponseDefiDaoImpl(Class<ReponseDefi> entityClass) {
        super(entityClass);
    }
}
