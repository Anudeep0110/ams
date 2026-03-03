package com.example.ams.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Component
public class OAuthClients {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
