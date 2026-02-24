package com.example.ams.repo;

import com.example.ams.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee , Long> {

    public List<Employee> findEmployeeByEmail(String Email);

    public List<Employee> findEmployeeByemployeeId(String employeeId);

    public List<Employee> findAll();

    public boolean existsById(String id);
}
