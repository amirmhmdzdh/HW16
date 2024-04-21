package org.hw16.service.impel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.exception.SignInException;
import org.hw16.model.Student;
import org.hw16.model.enums.StudentState;
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
    public Student signUp(String firstname, String lastname, String nationalCode, String password, String email) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            if (repository.findByNationalCodeAndPassword(nationalCode, password) != null ||
                    repository.findByFirstnameAndLastname(firstname, lastname) != null ||
                    repository.findByEmail(email) != null) {
                throw new SignInException("User with the same username, name, or email already exists");
            }

            Student newStudent = new Student();
            newStudent.setFirstName(firstname);
            newStudent.setLastName(lastname);
            newStudent.setNationalCode(nationalCode);
            newStudent.setPassword(password);
            newStudent.setEmail(email);
            newStudent.setStudentState(StudentState.ENROLLED);

            transaction.commit();
            return saveOrUpdate(newStudent);
        } catch (
                HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        }
    }
}