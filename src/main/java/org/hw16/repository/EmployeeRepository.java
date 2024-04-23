package org.hw16.repository;

import org.hw16.base.repository.BaseRepository;
import org.hw16.model.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    Employee findByNationalCodeAndPassword(String nationalCode, String password);
}
