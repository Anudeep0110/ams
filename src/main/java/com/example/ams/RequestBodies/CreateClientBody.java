package com.example.ams.RequestBodies;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CreateClientBody {
    private String email;
    private String scope;
    private String password;
}
