package org.hw16.repository.impel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.Student;
import org.hw16.repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpel extends BaseRepositoryImpel<Student, Long>
        implements StudentRepository {

    public StudentRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student findByNationalCodeAndPassword(String nationalCode, String password) {
        Transaction transaction = null;
        Student student = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Student WHERE nationalCode = :nationalCode AND password = :password";
            Query<Student> query = session.createQuery(hql, Student.class)
                    .setParameter("nationalCode", nationalCode)
                    .setParameter("password", password);

            student = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }

        return student;
    }

    @Override
    public Student findByFirstnameAndLastname(String firstname, String lastname) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Student WHERE firstName = :firstname AND lastName = :lastname";
        return session.createQuery(hql, Student.class)
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname)
                .uniqueResult();
    }

    @Override
    public Student findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Student WHERE email = :email";
        return session.createQuery(hql, Student.class)
                .setParameter("email", email)
                .uniqueResult();
    }

}