package com.example.ams.services;

import com.example.ams.models.Employee;
import com.example.ams.models.OAuthClients;
import com.example.ams.repo.EmployeeRepo;
import com.example.ams.repo.OAuthClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class OAuthClientsService{

    @Autowired
    private OAuthClients oauthclient;
    @Autowired
    private OAuthClientsRepo oauthrepo;
    @Autowired
    public PasswordEncoder passwordencoder;
    @Autowired
    private EmployeeRepo employeerepo;

    public OAuthClients createClient(@RequestBody Employee employee) {

        List<Employee> employees = employeerepo.findEmployeeByEmail(employee.getEmail());
        if (employees.isEmpty()) {
            throw new RuntimeException("User not found!");
        }else {
            Employee curr = employees.getFirst();
            if(passwordencoder.matches(employee.getPassword(), curr.getPassword())) {
                List<OAuthClients> allclients = oauthrepo.findByEmployee(curr);
                System.out.println(allclients);
                if (allclients.size() >= 5) {
                    throw new RuntimeException("Client credentials limit exceeded. Please contact admin support for assistance");
                } else {
                    String clientId = UUID.randomUUID().toString();
                    String clientSecret = UUID.randomUUID().toString();

                    String encodedSecret = passwordencoder.encode(clientSecret);

                    oauthclient.setClientId(clientId);
                    oauthclient.setClientSecret(encodedSecret);
                    oauthclient.setEmployee(curr);
                    System.out.println(curr);
                    System.out.println(oauthclient);
                    oauthrepo.save(oauthclient);

                    oauthclient.setClientSecret(clientSecret);

                    return oauthclient;
                }
            }else{
                throw new RuntimeException("Please check the username and password provided!");
            }
        }
    }
}
