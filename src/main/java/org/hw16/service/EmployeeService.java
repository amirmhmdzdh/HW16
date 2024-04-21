package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.Employee;

public interface EmployeeService extends BaseService<Employee,Long> {
    Employee signIn(String nationalCode, String password);
    Employee signUp(String firstname, String lastname, String username, String password, String email, Long salary);

}
