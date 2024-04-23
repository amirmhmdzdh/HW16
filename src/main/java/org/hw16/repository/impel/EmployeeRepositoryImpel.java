package org.hw16.repository.impel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hw16.base.repository.BaseRepositoryImpel;
import org.hw16.model.Employee;
import org.hw16.repository.EmployeeRepository;

public class EmployeeRepositoryImpel extends BaseRepositoryImpel<Employee, Long>
        implements EmployeeRepository {
    public EmployeeRepositoryImpel(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public Employee findByNationalCodeAndPassword(String nationalCode, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Employee WHERE nationalCode = :nationalCode AND password = :password";
        return session.createQuery(hql, Employee.class)
                .setParameter("nationalCode", nationalCode)
                .setParameter("password", password)
                .uniqueResult();
    }
}
