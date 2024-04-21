package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hw16.model.enums.StudentState;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("Student")
public class Student extends Person {

    @OneToMany(mappedBy = "student",
            fetch = FetchType.EAGER)
    private List<StudentTakenCourse> studentTakenCourseList;

    @Column(name = "total_credits")
    @Min(value = 0, message = "Invalid input for unit. It should be positive number.")
    private Integer totalCredit;

    private Double gpa;

    @Column(name = "student_state")
    @NotEmpty(message = "Please Enter your major correctly.")
    private StudentState studentState;

    public Student() {
    }

    public Student(List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }

    public Student(Long aLong, String firstname, String lastname, String nationalCode, String password, String email, List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        super(aLong, firstname, lastname, nationalCode, password, email);
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }

    public Student(String firstname, String lastname, String nationalCode, String password, String email, List<StudentTakenCourse> studentTakenCourseList, Integer totalCredit, Double gpa, StudentState studentState) {
        super(firstname, lastname, nationalCode, password, email);
        this.studentTakenCourseList = studentTakenCourseList;
        this.totalCredit = totalCredit;
        this.gpa = gpa;
        this.studentState = studentState;
    }

    public List<StudentTakenCourse> getStudentTakenCourseList() {
        return studentTakenCourseList;
    }

    public void setStudentTakenCourseList(List<StudentTakenCourse> studentTakenCourseList) {
        this.studentTakenCourseList = studentTakenCourseList;
    }

    public void setTotalCredit() {
        for (StudentTakenCourse takenCourse : studentTakenCourseList) {
            this.totalCredit += takenCourse.getReleasedCourse().getCourse().getCredit();
        }
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setGpa() {
        int number = 0;
        for (StudentTakenCourse takenCourse : studentTakenCourseList) {
            this.gpa += takenCourse.getMark() * takenCourse.getReleasedCourse().getCourse().getCredit();
            number++;
        }
        this.gpa /= totalCredit;
    }

    public Double getGpa() {
        return gpa;
    }

    public StudentState getStudentState() {
        return studentState;
    }

    public void setStudentState(StudentState studentState) {
        this.studentState = studentState;
    }

    @Override
    public String toString() {
        return "Student{" +
//                ", studentTakenCourseList=" + studentTakenCourseList +
                ", totalCredit=" + totalCredit +
                ", gpa=" + gpa +
                ", studentState=" + studentState +
                "} " + super.toString();
    }
}
