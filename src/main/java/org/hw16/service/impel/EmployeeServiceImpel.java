package org.hw16.service.impel;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hw16.base.service.BaseServiceImpel;
import org.hw16.exception.SignInException;
import org.hw16.model.Employee;
import org.hw16.repository.EmployeeRepository;
import org.hw16.service.EmployeeService;

public class EmployeeServiceImpel
        extends BaseServiceImpel<Employee, Long, EmployeeRepository>
        implements EmployeeService {
    public EmployeeServiceImpel(EmployeeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    public Employee signIn(String nationalCode, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Employee employee = repository.findByNationalCodeAndPassword(nationalCode, password);
            if (employee != null) {
                System.out.println("Signed in successfully!");
                transaction.commit();
                return employee;
            } else {
                transaction.rollback();
                throw new SignInException("Invalid national code or password. Failed.");
            }
        }
    }

    @Override
    public Employee signUp(Employee employee) {
        Transaction transaction = null;
        if (!isValid(employee))
            return null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Employee saveOrUpdate = repository.saveOrUpdate(employee);

            transaction.commit();
            session.close();
            return saveOrUpdate;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        }
    }
}