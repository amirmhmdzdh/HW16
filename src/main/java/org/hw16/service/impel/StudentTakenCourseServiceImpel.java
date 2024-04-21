package org.hw16.service.impel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.model.StudentTakenCourse;
import org.hw16.repository.StudentTakenCourseRepository;
import org.hw16.service.StudentTakenCourseService;

public class StudentTakenCourseServiceImpel
        extends BaseServiceImpel<StudentTakenCourse, Long, StudentTakenCourseRepository>
        implements StudentTakenCourseService {


    public StudentTakenCourseServiceImpel(StudentTakenCourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    public StudentTakenCourse addCourseByGpa(Student student, ReleasedCourse releasedCourse) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            StudentTakenCourse studentTakenCourse = repository.addCourseByGpa(student, releasedCourse);
            transaction.commit();
            session.close();
            return studentTakenCourse;
        } catch (Exception b) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(b.getMessage());
            return null;
        }
    }
}
