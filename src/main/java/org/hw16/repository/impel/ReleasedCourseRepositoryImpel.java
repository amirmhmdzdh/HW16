package org.hw16.repository.impel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.repository.ReleasedCourseRepository;

import java.util.List;

public class ReleasedCourseRepositoryImpel extends BaseRepositoryImpel<ReleasedCourse, Long>
        implements ReleasedCourseRepository {
    public ReleasedCourseRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<ReleasedCourse> getEntityClass() {
        return ReleasedCourse.class;
    }

    @Override
    public List<ReleasedCourse> findEligibleReleasedCoursesForStudent(Long studentId) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.find(Student.class, studentId);
        double gpa = student.getGpa();
        int totalCredits = student.getTotalCredit();

        String hql = "SELECT rc FROM ReleasedCourse rc " +
                "WHERE (:gpa >= 18 AND :totalCredits <= 24) " +
                "OR (:gpa < 18 AND :totalCredits <= 20)";

        return session.createQuery(hql, ReleasedCourse.class)
                .setParameter("gpa", gpa)
                .setParameter("totalCredits", totalCredits)
                .getResultList();
    }
}