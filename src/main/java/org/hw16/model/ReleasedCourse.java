package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hw16.base.entity.BaseEntity;

@Entity
@Getter
@Setter
@ToString
@Table(name = "released_course")
public class ReleasedCourse extends BaseEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
