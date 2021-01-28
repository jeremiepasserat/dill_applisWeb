package com.dill.api_rest.dao.jpa;

import com.dill.api_rest.dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AbstractDaoImpl<T> implements AbstractDao<T> {
    @PersistenceContext
    private EntityManager em;

    private Class<T> domainClass;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.domainClass = entityClass;
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public void edit(T entity) {
        em.merge(entity);
    }

    @Override
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public T find(Object id) {
        return em.find(domainClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(domainClass);
        criteria.select(criteria.from(domainClass));
        return em.createQuery(criteria).getResultList();
    }

 /*   *//* (non-Javadoc)
     * @see dao.AbstractDao#findRange(int[])
     *//*
    @Override
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(domainClass);
        cq.select(cq.from(domainClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    *//* (non-Javadoc)
     * @see dao.AbstractDao#count()
     *//*
    @Override
    public int count() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = b.createQuery(Long.class);
        criteria.select(b.count(criteria.from(domainClass)));

        return em.createQuery(criteria).getSingleResult().intValue();
    }*/
}
