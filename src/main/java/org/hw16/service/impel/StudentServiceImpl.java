package org.hw16.service.impel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.exception.SignInException;
import org.hw16.model.Student;
import org.hw16.repository.StudentRepository;
import org.hw16.service.StudentService;

public class StudentServiceImpl
        extends BaseServiceImpel<Student, Long, StudentRepository>
        implements StudentService {
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Student signIn(String nationalCode, String password) {
        Student student = repository.findByNationalCodeAndPassword(nationalCode, password);
        if (student != null) {
            System.out.println("Signed in successfully!");
            return student;
        } else {
            throw new SignInException("Invalid national code or password. failed.");
        }
    }

    @Override
    public Student signUp(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Student saveOrUpdate = repository.saveOrUpdate(student);
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