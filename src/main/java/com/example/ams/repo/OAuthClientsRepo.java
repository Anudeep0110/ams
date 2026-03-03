package com.example.ams.repo;

import com.example.ams.models.Employee;
import com.example.ams.models.OAuthClients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OAuthClientsRepo extends JpaRepository<OAuthClients, UUID> {
    public List<OAuthClients> findByClientId(String clientId);

    public List<OAuthClients> findByEmployee(Employee employee);
}
