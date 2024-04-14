package org.hw16.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
@DiscriminatorValue("Employee")
public class Employee extends Person {

    @Min(10_000_000)
    private Long salary;


    public Employee() {
    }

    public Employee(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                "} " + super.toString();
    }
}
