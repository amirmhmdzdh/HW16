package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hw16.base.entity.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "released_course")
public class ReleasedCourse extends BaseEntity<Long> {

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Override
    public String toString() {
        return "ReleasedCourse{" +
                "teacher=" + teacher.getLastName() + " " + teacher.getEmail() +
                ", course=" + course +
                ", semester=" + semester +
                '}';
    }
}
