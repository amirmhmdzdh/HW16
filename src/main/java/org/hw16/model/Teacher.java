package org.hw16.model;

import jakarta.persistence.*;
import org.hw16.model.enums.TeacherLevel;

import javax.validation.constraints.Min;
import java.util.List;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {

    @Min(value = 0, message = "Invalid input for unit. It should be positive number.")
    @Column(name = "total_credit")
    private Integer totalCredit;

    @Min(value = 0, message = "Invalid input for salary. It should be positive number.")
    @Column(name = "base_salary")
    private Long baseSalary;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_level")
    private TeacherLevel teacherLevel;

    @OneToMany(mappedBy = "teacher",
            fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ReleasedCourse> releasedCourses;

    public Teacher() {
    }

    public Teacher(Long aLong, String firstname, String lastname, String nationalCode, String password, String email, Long baseSalary, TeacherLevel teacherLevel) {
        super(aLong, firstname, lastname, nationalCode, password, email);
        this.baseSalary = baseSalary;
        this.teacherLevel = teacherLevel;
    }

    public Teacher(String firstname, String lastname, String nationalCode, String password, String email, long baseSalary, TeacherLevel teacherLevel) {
        super(firstname, lastname, nationalCode, password, email);
        this.baseSalary = baseSalary;
        this.teacherLevel = teacherLevel;
    }


    public void setTotalCredit(int credit) {
//        this.totalCredit = 0;
//        for (ReleasedCourse taughtCourse : releasedCourses) {
//            this.totalCredit += taughtCourse.getCourse().getCredit();
//        }
        this.totalCredit = credit;
    }

    public Integer getTotalCredit() {
        int totalCredit = 0;

        for (ReleasedCourse taughtCourse : releasedCourses) {
            totalCredit += taughtCourse.getCourse().getCredit();
        }

        return totalCredit;
    }


    public void setTeacherLevel(TeacherLevel teacherLevel) {
        this.teacherLevel = teacherLevel;
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

    public TeacherLevel getTeacherLevel() {
        return teacherLevel;
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
//                ", releasedCourses=" + releasedCourses +
                "} " + super.toString();
    }
}
