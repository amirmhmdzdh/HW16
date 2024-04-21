package org.hw16.repository;

import org.hw16.base.repository.BaseRepository;
import org.hw16.model.Student;

public interface StudentRepository extends BaseRepository<Student, Long> {
    Student findByNationalCodeAndPassword(String NationalCode, String password);

    Student findByFirstnameAndLastname(String firstname, String lastname);

    Student findByEmail(String email);

}
