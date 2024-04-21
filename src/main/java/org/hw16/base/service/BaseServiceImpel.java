package org.hw16.base.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.entity.BaseEntity;
import org.hw16.base.repository.BaseRepository;
import org.hw16.exception.NotFoundExeption;
import org.hw16.utility.EntityValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BaseServiceImpel<T extends BaseEntity<ID>,
        ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    protected final R repository;
    protected final SessionFactory sessionFactory;
    private final Validator validator;

    public BaseServiceImpel(R repository, SessionFactory sessionFactory) {
        this.repository = repository;
        this.sessionFactory = sessionFactory;
        this.validator = EntityValidator.validator;
    }

    @Override
    public T saveOrUpdate(T entity) {
        Transaction transaction = null;

        if (!isValid(entity))
            return null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            T t = repository.saveOrUpdate(entity);

            transaction.commit();
            session.close();
            return t;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Optional<T> findById(ID id) {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<T> findEntity = repository.findById(id);
            findEntity.orElseThrow(() -> new NotFoundExeption(String.format("Entity with id : %s not found", id)));
            session.getTransaction().commit();
            session.close();
            return findEntity;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<T> findEntity = repository.findById(id);
            findEntity.orElseThrow(() -> new NotFoundExeption(String.format("Entity with id : %s not found", id)));
            repository.delete(findEntity.get());
            session.getTransaction().commit();
        } catch (Exception ignored) {
        }
    }

    @Override
    public List<T> showAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<T> showAll = repository.showAll();
            session.getTransaction().commit();
            session.close();
            return showAll;
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public boolean isValid(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> p : violations)
                System.out.println(p.getMessage());
            return false;
        }
        return true;
    }
}