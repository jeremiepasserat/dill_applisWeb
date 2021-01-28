package com.dill.api_rest.dao;

import java.util.List;

public interface AbstractDao<T> {

    public abstract void create(T entity);

    public abstract void edit(T entity);

    public abstract void remove(T entity);

    public abstract T find(Object id);

    public abstract List<T> findAll();

    //public abstract List<T> findRange(int[] range);

    //public abstract int count();

}
