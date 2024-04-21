package org.hw16.service.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.repository.ReleasedCourseRepository;
import org.hw16.service.ReleasedCourseService;

import java.util.List;

public class ReleasedCourseServiceImpel
        extends BaseServiceImpel<ReleasedCourse, Long, ReleasedCourseRepository>
        implements ReleasedCourseService {
    public ReleasedCourseServiceImpel(ReleasedCourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<ReleasedCourse> findEligibleReleasedCoursesForStudent(Long studentId) {
        if (studentId == null || studentId < 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }

        return repository.findEligibleReleasedCoursesForStudent(studentId);
    }
}
