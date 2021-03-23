package com.uniloftsky.spingframework.spring5advertismentservice.services;

import java.util.Set;

public interface GenericService<T, ID> {

    Set<T> findAll();
    T findById(ID id);
    T save(T obj);
    void delete(T obj);

}
