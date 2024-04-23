package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.Teacher;

public interface TeacherService extends BaseService<Teacher, Long> {
    Teacher signIn(String username, String password);

    Teacher signUp(Teacher teacher);



}
