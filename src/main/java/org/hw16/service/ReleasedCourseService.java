package org.hw16.service;

import org.hw16.base.service.BaseService;
import org.hw16.model.ReleasedCourse;

import java.util.List;

public interface ReleasedCourseService extends BaseService<ReleasedCourse,Long> {
    List<ReleasedCourse> findEligibleReleasedCoursesForStudent(Long studentId);
}
