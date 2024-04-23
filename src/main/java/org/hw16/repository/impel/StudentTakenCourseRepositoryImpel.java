package org.hw16.repository.impel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.model.StudentTakenCourse;
import org.hw16.repository.StudentTakenCourseRepository;

public class StudentTakenCourseRepositoryImpel extends BaseRepositoryImpel<StudentTakenCourse, Long>
        implements StudentTakenCourseRepository {
    public StudentTakenCourseRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<StudentTakenCourse> getEntityClass() {
        return StudentTakenCourse.class;
    }

    public StudentTakenCourse addCourseByGpa(Student student, ReleasedCourse releasedCourse) {
        Session session = sessionFactory.getCurrentSession();

        student.getTotalCredit();

        StudentTakenCourse newTakenCourse = new StudentTakenCourse();
        newTakenCourse.setReleasedCourse(releasedCourse);
        newTakenCourse.setStudent(student);
        student.getStudentTakenCourseList().add(newTakenCourse);
        student.setTotalCredit();

        session.merge(student);
        session.persist(newTakenCourse);

        return newTakenCourse;
    }
}