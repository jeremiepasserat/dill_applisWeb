package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.modele.GeolocalisationVille;

public class GeolocalisationVilleDaoImpl extends AbstractDaoImpl<GeolocalisationVille> implements GeolocalisationVilleDao {
    public GeolocalisationVilleDaoImpl(Class<GeolocalisationVille> entityClass) {
        super(entityClass);
    }
}
