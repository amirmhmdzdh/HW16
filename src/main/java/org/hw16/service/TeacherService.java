package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.Teacher;
import org.hw16.model.enums.TeacherLevel;

public interface TeacherService extends BaseService<Teacher, Long> {
    Teacher signIn(String username, String password);

    Teacher signUp(String firstname, String lastname, String username, String password, String email,
                   Long baseSalary, TeacherLevel teacherLevel);



}
