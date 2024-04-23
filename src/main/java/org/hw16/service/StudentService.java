package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.Student;


public interface StudentService extends BaseService<Student, Long> {
    Student signIn(String nationalCode, String password);

    Student signUp(Student student);
}
