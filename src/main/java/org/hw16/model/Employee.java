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

    @Min(1_000_000)
    private Long salary;


    public Employee() {
    }

    public Employee(String firstname, String lastname, String nationalCode, String password, String email, Long salary) {
        super(firstname, lastname, nationalCode, password, email);
        this.salary = salary;
    }

    public Employee(Long aLong, String firstname, String lastname, String nationalCode, String password, String email, Long salary) {
        super(aLong, firstname, lastname, nationalCode, password, email);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                "} " + super.toString();
    }
}
