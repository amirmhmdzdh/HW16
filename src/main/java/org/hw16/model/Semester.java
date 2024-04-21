package org.hw16.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hw16.base.entity.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Semester extends BaseEntity<Long> {

    @Size(max = 4, min = 4, message = "Enter a valid academic semester.")
    @NotNull(message = "Please Enter Semester.")
    private Integer season;

    @Size(max = 4, min = 4, message = "Enter a valid academic Year.")
    @NotNull(message = "Please Enter Year.")
    private Integer year;


    public Semester(Long aLong, Integer season, Integer year) {
        super(aLong);
        this.season = season;
        this.year = year;
    }

    public Semester(Integer season, Integer year) {
        this.season = season;
        this.year = year;
    }
}
