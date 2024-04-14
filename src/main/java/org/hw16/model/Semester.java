package org.hw16.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hw16.base.entity.BaseEntity;
import org.hw16.model.enums.Season;

import java.time.Year;

@Getter
@Setter
@Entity
public class Semester extends BaseEntity<Long> {

    private Season season;

    private Integer year;

    @PrePersist
    private void prePersist() {
        year = Year.now().getValue();
    }
}