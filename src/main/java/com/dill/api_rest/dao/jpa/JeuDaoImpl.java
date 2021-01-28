package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.dao.JeuDao;
import com.dill.api_rest.modele.GeolocalisationVille;
import com.dill.api_rest.modele.Jeu;

public class JeuDaoImpl  extends AbstractDaoImpl<Jeu> implements JeuDao {
    public JeuDaoImpl(Class<Jeu> entityClass) {
        super(entityClass);
    }
}
