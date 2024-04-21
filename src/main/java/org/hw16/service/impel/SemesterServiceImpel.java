package org.hw16.service.impel;

import org.hibernate.SessionFactory;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.model.Semester;
import org.hw16.repository.SemesterRepository;
import org.hw16.service.SemesterService;

public class SemesterServiceImpel
extends BaseServiceImpel<Semester,Long, SemesterRepository>
implements SemesterService {
    public SemesterServiceImpel(SemesterRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }


}
