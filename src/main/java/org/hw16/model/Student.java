package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hw16.model.enums.StudentState;

import java.util.List;

@Getter
@Entity
@DiscriminatorValue("Student")
public class Student extends Person {

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

    public Student(String firstname, String lastname, String nationalCode, String password, String email) {
        super(firstname, lastname, nationalCode, password, email);
    }

    public List<StudentTakenCourse> getStudentTakenCourseList() {
        return studentTakenCourseList;
    }

    public void setStudentTakenCourseList(List<StudentTakenCourse> studentTakenCourseList) {
        this.studentTakenCourseList = studentTakenCourseList;
    }

    public void setTotalCredit() {
        int totalCredits = 0;
        for (StudentTakenCourse takenCourse : studentTakenCourseList) {
            totalCredits += takenCourse.getReleasedCourse().getCourse().getCredit();
        }
        this.totalCredit = totalCredits;
    }

    public Integer getTotalCredit() {
        return totalCredit = 0;
    }

    public void setGpa(Student student) {
        int totalCredits = 0;
        double weightedSum = 0.0;

        for (StudentTakenCourse takenCourse : student.getStudentTakenCourseList()) {
            if (takenCourse.getStudent().getId() == student.getId()) { // فیلتر کردن دروس بر اساس آیدی دانش‌آموز
                int credit = takenCourse.getReleasedCourse().getCourse().getCredit();
                double mark = takenCourse.getMark();

                weightedSum += mark * credit;
                totalCredits += credit;
            }
        }

        if (totalCredits != 0) {
            this.gpa = weightedSum / totalCredits;
        } else {
            this.gpa = 0.0; // یا می‌توانید با کمک اطلاعات موجود، مقدار پیش‌فرض معدل را مشخص کنید
        }
    }

    public Double getGpa() {
        return gpa = 0.0;
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
