package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.BadgeDao;
import com.dill.api_rest.modele.Badge;

public class BadgeDaoImpl extends AbstractDaoImpl<Badge> implements BadgeDao {
    public BadgeDaoImpl(Class<Badge> entityClass) {
        super(entityClass);
    }
}
