package com.example.ams.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
//import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    private String Id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    private String status;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String role;

}
