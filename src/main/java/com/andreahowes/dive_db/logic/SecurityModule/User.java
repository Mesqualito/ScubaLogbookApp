package com.andreahowes.dive_db.logic.SecurityModule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column
    @Size(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column
    @NotEmpty(message = "*Please provide your first name")
    private String firstName;

    @Column
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Role> roles;

    public User(int id, @Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email,
                @Size(min = 5, message = "*Your password must have at least 5 characters")
                @NotEmpty(message = "*Please provide your password") String password,
                @NotEmpty(message = "*Please provide your first name") String firstName,
                @NotEmpty(message = "*Please provide your last name") String lastName, int active, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
    }
}
