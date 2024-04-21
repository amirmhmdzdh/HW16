package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hw16.base.entity.BaseEntity;
import org.hw16.model.enums.CourseState;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Getter
@Setter
@ToString
@Table(name = "student_taken_course")
public class StudentTakenCourse extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "released_course_id")
    private ReleasedCourse releasedCourse;

    @DecimalMin(value = "0.0", message = "The scoring floor is 0.0 points")
    @DecimalMax(value = "20.0", message = "The scoring limit is 20 points")
    private Double mark;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_state")
    private CourseState courseState;

    @ManyToOne
    private Student student;

    public StudentTakenCourse() {
    }

    public StudentTakenCourse(ReleasedCourse releasedCourse, Double mark, Student student) {
        this.releasedCourse = releasedCourse;
        this.mark = mark;
        this.student = student;
    }

    public StudentTakenCourse(Long aLong, ReleasedCourse releasedCourse, Double mark, Student student) {
        super(aLong);
        this.releasedCourse = releasedCourse;
        this.mark = mark;
        this.student = student;
    }

    private void setCourseState() {
        if (this.mark < 10) {
            this.courseState = CourseState.FAILED;
        } else
            this.courseState = CourseState.PASSED;
    }


}