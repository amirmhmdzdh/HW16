package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hw16.base.entity.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class Person extends BaseEntity<Long> {

    @Pattern(regexp="^[a-zA-Z]+$", message = "FirstName must consist of letters only")
    private String firstname;

    @Pattern(regexp="^[a-zA-Z]+$", message = "LastName must consist of letters only")
    private String lastname;

    @Pattern(regexp = "^\\d{10}$", message = "nationalCod must consist of numbers only")
    private String nationalCode;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must consist of letters and numbers only")
    private String password;

    @Email(message = "Email should be valid")
    private String email;


    public Person() {
    }

    public Person(Long aLong, String firstname, String lastname, String nationalCode, String password, String email) {
        super(aLong);
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.password = password;
        this.email = email;
    }

    public Person(String firstname, String lastname, String nationalCode, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.password = password;
        this.email = email;
    }
}
