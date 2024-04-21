package org.hw16.service;


import org.hw16.base.service.BaseService;
import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.model.StudentTakenCourse;

public interface StudentTakenCourseService extends BaseService<StudentTakenCourse, Long> {
    StudentTakenCourse addCourseByGpa(Student student, ReleasedCourse releasedCourse);
}
