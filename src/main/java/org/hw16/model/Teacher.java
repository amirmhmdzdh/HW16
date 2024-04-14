package org.hw16.model;

import jakarta.persistence.*;
import org.hw16.model.enums.TeacherLevel;

import java.util.List;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {

    @Column(name = "total_credit")
    private Integer totalCredit;

    @Column(name = "base_salary")
    private Long baseSalary;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_level")
    private TeacherLevel teacherLevel;

    @Column(name = "total_salary")
    private Long totalSalary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teacher_department_id")
    private Department teacherDepartment;

    @OneToMany(mappedBy = "teacher",
            fetch = FetchType.LAZY)
    private List<ReleasedCourse> releasedCourses;

    public Teacher() {
    }

    public Teacher(Integer totalCredit,
                   Long baseSalary,
                   TeacherLevel teacherLevel,
                   Long totalSalary,
                   Department teacherDepartment,
                   List<ReleasedCourse> releasedCourses) {
        this.totalCredit = totalCredit;
        this.baseSalary = baseSalary;
        this.teacherLevel = teacherLevel;
        this.totalSalary = totalSalary;
        this.teacherDepartment = teacherDepartment;
        this.releasedCourses = releasedCourses;
    }

    public void setTotalCredit() {
        for (ReleasedCourse taughtCourse : releasedCourses) {
            this.totalCredit += taughtCourse.getCourse().getCredit();
        }
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setBaseSalary(Long baseSalary) {
        if (this.teacherLevel.equals(TeacherLevel.ADJUNCT))
            this.baseSalary = 0L;
        else
            this.baseSalary = baseSalary;
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public void setTotalSalary() {
        this.totalSalary = this.baseSalary + this.totalCredit * 1_000_000L;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public TeacherLevel getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(TeacherLevel teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public Department getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(Department teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public List<ReleasedCourse> getReleasedCourses() {
        return releasedCourses;
    }

    public void setReleasedCourses(List<ReleasedCourse> releasedCourses) {
        this.releasedCourses = releasedCourses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "totalCredit=" + totalCredit +
                ", baseSalary=" + baseSalary +
                ", teacherLevel=" + teacherLevel +
                ", totalSalary=" + totalSalary +
                ", teacherDepartment=" + teacherDepartment +
                ", releasedCourses=" + releasedCourses +
                "} " + super.toString();
    }
}