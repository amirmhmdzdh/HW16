package org.hw16.repository.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.Semester;
import org.hw16.repository.SemesterRepository;

public class SemesterRepositoryImpel extends BaseRepositoryImpel<Semester, Long>
        implements SemesterRepository {
    public SemesterRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Semester> getEntityClass() {
        return Semester.class;
    }
}
