package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.Student;

import java.util.List;

public interface StudentService extends BaseService<Student, Long> {
    Student signIn(String nationalCode, String password);

    Student signUp(String firstname, String lastname, String nationalCode, String password, String email);
}
