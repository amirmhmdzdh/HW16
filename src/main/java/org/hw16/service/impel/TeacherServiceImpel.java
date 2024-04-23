package org.hw16.service.impel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.exception.SignInException;
import org.hw16.model.Teacher;
import org.hw16.repository.TeacherRepository;
import org.hw16.service.TeacherService;

public class TeacherServiceImpel
        extends BaseServiceImpel<Teacher, Long, TeacherRepository>
        implements TeacherService {
    public TeacherServiceImpel(TeacherRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    public Teacher signIn(String nationalCode, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Teacher teacher = repository.findByNationalCodeAndPassword(nationalCode, password);
            if (teacher != null) {
                System.out.println("Signed in successfully!");
                transaction.commit();
                return teacher;
            } else {
                transaction.rollback();
                throw new SignInException("Invalid national code or password. Failed.");
            }
        }
    }

    @Override
    public Teacher signUp(Teacher teacher) {
        Transaction transaction = null;
        if (!isValid(teacher))
            return null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Teacher saveOrUpdate = repository.saveOrUpdate(teacher);
            transaction.commit();
            session.close();
            return saveOrUpdate;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        }
    }
}