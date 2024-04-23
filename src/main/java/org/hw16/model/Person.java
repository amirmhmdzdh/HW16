package org.hw16.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hw16.base.entity.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class Person extends BaseEntity<Long> {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName must consist of letters only")
    @Size(max = 50, min = 3, message = "Invalid name. Size should be between 3 to 20.")
    @NotEmpty(message = "Please Enter your name.")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName must consist of letters only")
    @Size(max = 50, min = 3, message = "Invalid name. Size should be between 3 to 20.")
    @NotEmpty(message = "Please Enter your lastName.")
    private String lastName;

    @Pattern(regexp = "^\\d{10}$", message = "nationalCod must consist of numbers only")
    @NotEmpty(message = "Please Enter your NationalCode.")
    @Column(unique = true)
    private String nationalCode;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must consist of letters and numbers only")
    @NotEmpty(message = "Please Enter your Password.")
    private String password;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Please Enter your Email.")
    @Column(unique = true)
    private String email;


    public Person() {
    }

    public Person(Long aLong, String firstName, String lastName, String nationalCode, String password, String email) {
        super(aLong);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.password = password;
        this.email = email;
    }

    public Person(String firstName, String lastName, String nationalCode, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.password = password;
        this.email = email;
    }
}
