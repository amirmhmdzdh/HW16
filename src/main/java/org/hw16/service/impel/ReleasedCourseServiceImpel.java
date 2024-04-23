package org.hw16.service.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.model.ReleasedCourse;
import org.hw16.repository.ReleasedCourseRepository;
import org.hw16.service.ReleasedCourseService;
public class ReleasedCourseServiceImpel
        extends BaseServiceImpel<ReleasedCourse, Long, ReleasedCourseRepository>
        implements ReleasedCourseService {
    public ReleasedCourseServiceImpel(ReleasedCourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
