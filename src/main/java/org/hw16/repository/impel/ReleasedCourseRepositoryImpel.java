package org.hw16.repository.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.repository.ReleasedCourseRepository;

public class ReleasedCourseRepositoryImpel extends BaseRepositoryImpel<ReleasedCourse, Long>
        implements ReleasedCourseRepository {
    public ReleasedCourseRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<ReleasedCourse> getEntityClass() {
        return ReleasedCourse.class;
    }
}