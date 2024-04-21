package org.hw16.repository.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.Course;
import org.hw16.repository.CourseRepository;

public class CourseRepositoryImpel extends BaseRepositoryImpel<Course, Long>
        implements CourseRepository {
    public CourseRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }
}
