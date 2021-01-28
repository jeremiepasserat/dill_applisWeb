package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.dao.JoueurDao;
import com.dill.api_rest.modele.GeolocalisationVille;
import com.dill.api_rest.modele.Joueur;

public class JoueurDaoImpl  extends AbstractDaoImpl<Joueur> implements JoueurDao {
    public JoueurDaoImpl(Class<Joueur> entityClass) {
        super(entityClass);
    }
}
