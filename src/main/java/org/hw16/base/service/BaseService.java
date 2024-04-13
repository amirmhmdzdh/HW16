package org.hw16.base.service;

import org.hw16.base.entity.BaseEntity;

import java.io.Serializable;
import java.net.IDN;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    List<T> findAll();


}
