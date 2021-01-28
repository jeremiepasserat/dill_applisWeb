package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.GeolocalisationVilleDao;
import com.dill.api_rest.dao.MessageCMJDao;
import com.dill.api_rest.modele.GeolocalisationVille;
import com.dill.api_rest.modele.MessageCMJ;

public class MessageCMJDaoImpl  extends AbstractDaoImpl<MessageCMJ> implements MessageCMJDao {
    public MessageCMJDaoImpl(Class<MessageCMJ> entityClass) {
        super(entityClass);
    }
}
