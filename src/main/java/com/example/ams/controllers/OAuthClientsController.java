package com.example.ams.controllers;

import com.example.ams.models.Employee;
import com.example.ams.models.OAuthClients;
import com.example.ams.services.OAuthClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class OAuthClientsController {

    @Autowired
    private OAuthClientsService oauthservice;

    @Autowired
    private PasswordEncoder passwordencoder;

    @PostMapping("/createclient")
    public ResponseEntity<HashMap<String,String>> createClient(@RequestBody Employee employee) {
        try {
            System.out.println(employee);
            OAuthClients oauthclient = oauthservice.createClient(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of(
                    "status","1",
                    "clientId", oauthclient.getClientId(),
                    "clientSecret",oauthclient.getClientSecret(),
                    "message", "Please copy and store the client credentials as these will only visible once."
            )));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of(
                    "status","1",
                    "message",e.getMessage()
            )));
        }
    }
}
