package org.hw16.repository;

import org.hw16.base.repository.BaseRepository;
import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.model.StudentTakenCourse;

public interface StudentTakenCourseRepository extends BaseRepository<StudentTakenCourse, Long> {
    StudentTakenCourse addCourseByGpa(Student student, ReleasedCourse releasedCourse);
}
