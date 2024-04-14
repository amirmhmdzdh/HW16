package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hw16.model.enums.StudentState;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("Student")
public class Student extends Person {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_department_id")
    private Department studentDepartment;

    @OneToMany(mappedBy = "student",
            fetch = FetchType.EAGER)
    private List<StudentTakenCourse> studentTakenCourseList;

    @Column(name = "total_credits")
    private Integer totalCredit;

    private Double gpa;

    @Column(name = "student_state")
    private StudentState studentState;

    public Student() {
    }

    public Student(Department studentDepartment, List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        this.studentDepartment = studentDepartment;
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }

    public Student(Long aLong, String firstname, String lastname, String nationalCode, String password, String email, Department studentDepartment, List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        super(aLong, firstname, lastname, nationalCode, password, email);
        this.studentDepartment = studentDepartment;
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }

    public Student(String firstname, String lastname, String nationalCode, String password, String email, Department studentDepartment, List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        super(firstname, lastname, nationalCode, password, email);
        this.studentDepartment = studentDepartment;
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }
}
