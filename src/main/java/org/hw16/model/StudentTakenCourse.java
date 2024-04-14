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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "released_course_id")
    private ReleasedCourse releasedCourse;

    @DecimalMin(value = "0.0", message = "Mark must be greater than or equal to 0.0")
    @DecimalMax(value = "20.0", message = "Mark must be less than or equal to 10000.0")
    private Double mark;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_state")
    private CourseState courseState;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Student student;

    private void setCourseState(){
        if (this.mark < 10){
            this.courseState = CourseState.FAILED;
        } else
            this.courseState = CourseState.PASSED;
    }


}