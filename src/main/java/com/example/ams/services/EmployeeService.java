package com.example.ams.services;

import com.example.ams.models.Employee;
import com.example.ams.repo.EmployeeRepo;
import com.example.ams.utilities.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepo employeerepo ;
    private final IDGenerator generator;

    public EmployeeService(EmployeeRepo repo,IDGenerator generator) {
        this.employeerepo = repo;
        this.generator = generator;
    }

    public void createEmployee(Employee emp) {
        if (employeerepo.findEmployeeByEmail(emp.getEmail()).isEmpty() & employeerepo.findEmployeeByemployeeId(emp.getEmployeeId()).isEmpty()) {
            String Id;
            do{
                Id = generator.generate(12);
            } while(employeerepo.existsById(Id));

            emp.setId(Id);
            employeerepo.save(emp);
            return;
        }
        throw new RuntimeException("Employee with same email id/employee id already exists!");
    }

    public void updateEmployee(Employee emp) {

        if(emp.getEmail() == null && emp.getEmployeeId() == null) {
            throw new RuntimeException("Please provide employeeId or email in the request body");
        }else{
            if(emp.getEmail() != null && !emp.getEmail().isEmpty()) {
                if (employeerepo.findEmployeeByEmail(emp.getEmail()).isEmpty()) {
                    throw new RuntimeException("Employee with Email ID "+emp.getEmail()+" not found!");
                }else{
                    List<Employee> curr = employeerepo.findEmployeeByEmail(emp.getEmail());
                    if (!curr.isEmpty()) {
                        Employee curEmp = curr.get(0);
                        if (emp.getFirstName() != null && !emp.getFirstName().isEmpty()) {
                            curEmp.setFirstName(emp.getFirstName());
                        }
                        if (emp.getLastName() != null && !emp.getLastName().isEmpty()) {
                            curEmp.setLastName(emp.getLastName());
                        }
                        employeerepo.save(curEmp);
                    }else{
                        throw new RuntimeException("Something went Wrong!");
                    }
                }
            }else{
                List<Employee> curr = employeerepo.findEmployeeByemployeeId(emp.getEmployeeId());
                if (!curr.isEmpty()) {
                    Employee curEmp = curr.get(0);
                    if (emp.getFirstName() != null && !emp.getFirstName().isEmpty()) {
                        curEmp.setFirstName(emp.getFirstName());
                    }
                    if (emp.getLastName() != null && !emp.getLastName().isEmpty()) {
                        curEmp.setLastName(emp.getLastName());
                    }
                    employeerepo.save(curEmp);
                }else{
                    throw new RuntimeException("Something went Wrong!");
                }
            }
        }
        return;
    }

    public List<Employee> getAllEmployees() {
        return employeerepo.findAll();
    }

    public List<Employee> getEmployeeProfile(String email) {
        return employeerepo.findEmployeeByEmail(email);
    }
}
