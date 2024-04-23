package org.hw16.repository;

import org.hw16.base.repository.BaseRepository;
import org.hw16.model.Teacher;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher, Long> {
    Teacher findByNationalCodeAndPassword(String nationalCode, String password);

}
