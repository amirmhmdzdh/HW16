package org.hw16.base.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hw16.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpel<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID> {

    protected SessionFactory sessionFactory;

    public BaseRepositoryImpel(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T saveOrUpdate(T entity) {

        Session session = sessionFactory.getCurrentSession();
        if (entity.getId() == null)
            session.persist(entity);
        else
            session.merge(entity);
        return entity;

    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(getEntityClass(), id));
    }

    public abstract Class<T> getEntityClass();

    @Override
    public void delete(T entity) {

        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<T> query = session.createQuery(String.format("from %s", getEntityClass()));
        return query.getResultList();
    }

    @Override
    public List<T> showAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
    }

}
