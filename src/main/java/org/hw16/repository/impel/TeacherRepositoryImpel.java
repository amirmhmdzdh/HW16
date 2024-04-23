package org.hw16.repository.impel;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.Teacher;
import org.hw16.repository.TeacherRepository;

import java.util.List;

public class TeacherRepositoryImpel extends BaseRepositoryImpel<Teacher, Long>
        implements TeacherRepository {
    public TeacherRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public Teacher findByNationalCodeAndPassword(String nationalCode, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Teacher t WHERE t.nationalCode = :nationalCode AND t.password = :password";
        Query<Teacher> query = session.createQuery(hql, Teacher.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("password", password);
        List<Teacher> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}