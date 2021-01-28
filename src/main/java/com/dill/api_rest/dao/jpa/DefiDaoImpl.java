package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.DefiDao;
import com.dill.api_rest.modele.Defi;

public class DefiDaoImpl extends AbstractDaoImpl<Defi> implements DefiDao {
    public DefiDaoImpl(Class<Defi> entityClass) {
        super(entityClass);
    }
}
