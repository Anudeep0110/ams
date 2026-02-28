package com.example.ams.services;

import com.example.ams.models.Employee;
import com.example.ams.repo.EmployeeRepo;
import com.example.ams.security.CustomUserDetails;
import com.example.ams.utilities.HandlePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo emprepo;

    @Autowired
    private HandlePassword handlePassword;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        List<Employee> users = emprepo.findEmployeeByEmail(username);
        if(users.isEmpty()) {
            throw new RuntimeException("User not found!");
        }else{
            Employee emp = users.getFirst();
            return new CustomUserDetails(emp);
        }

    }

}
