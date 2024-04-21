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

    public Employee signUp(String firstname, String lastname, String nationalCode, String password, String email, Long salary) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            if (repository.findByNationalCodeAndPassword(nationalCode, password) != null ||
                    repository.findByFirstnameAndLastname(firstname, lastname) != null ||
                    repository.findByEmail(email) != null) {
                throw new SignInException("User with the same username, name, or email already exists");
            }

            Employee newEmployee = new Employee();
            newEmployee.setFirstName(firstname);
            newEmployee.setLastName(lastname);
            newEmployee.setNationalCode(nationalCode);
            newEmployee.setPassword(password);
            newEmployee.setEmail(email);
            newEmployee.setSalary(salary);

            transaction.commit();
            return saveOrUpdate(newEmployee);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        }
    }
}