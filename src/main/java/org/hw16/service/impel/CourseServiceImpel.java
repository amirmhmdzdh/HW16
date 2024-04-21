package org.hw16.service.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.model.Course;
import org.hw16.repository.CourseRepository;
import org.hw16.service.CourseService;

public class CourseServiceImpel extends BaseServiceImpel<Course, Long, CourseRepository>
        implements CourseService {
    public CourseServiceImpel(CourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
