package org.hw16.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hw16.base.entity.BaseEntity;

import javax.validation.constraints.*;

@Getter
@Setter
@Entity
public class Course extends BaseEntity<Long> {

    @NotBlank(message = "Course title is required")
    @Size(max = 100, message = "Course title must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Course title must consist of letters and numbers only")
    private String title;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits cannot exceed 10")
    private Integer credit;


    public Course() {
    }

    public Course(String title, Integer credit) {
        this.title = title;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", credit=" + credit +
                '}';
    }
}
