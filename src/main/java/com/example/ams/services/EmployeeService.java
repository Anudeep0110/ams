package com.example.ams.services;

import aj.org.objectweb.asm.Handle;
import com.example.ams.models.Employee;
import com.example.ams.repo.EmployeeRepo;
import com.example.ams.utilities.HandlePassword;
import com.example.ams.utilities.IDGenerator;
import com.example.ams.views.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepo employeerepo ;
    private final IDGenerator generator;
    private HandlePassword  passwordHandler;
    public EmployeeService(EmployeeRepo repo,IDGenerator generator,HandlePassword passwordHandler) {
        this.employeerepo = repo;
        this.generator = generator;
        this.passwordHandler = passwordHandler;
    }

    public void createEmployee(Employee emp) {
        if (employeerepo.findEmployeeByEmail(emp.getEmail()).isEmpty() & employeerepo.findEmployeeByemployeeId(emp.getEmployeeId()).isEmpty()) {
            String Id;
            do{
                Id = generator.generate(12);
            } while(employeerepo.existsById(Id));

            emp.setId(Id);
            emp.setStatus("Active");
            emp.setPassword(passwordHandler.passwordEncoder().encode(emp.getPassword()));
            emp.setRole("USER");
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
                        if (emp.getStatus() != null && !emp.getStatus().isEmpty()) {
                            curEmp.setStatus(emp.getStatus());
                        }

                        if (emp.getRole() != null && !emp.getRole().isEmpty()) {
                            curEmp.setRole(emp.getRole());
                        }

                        if (emp.getPassword() != null && !emp.getPassword().isEmpty()) {
                            curEmp.setPassword(passwordHandler.passwordEncoder().encode(emp.getPassword()));
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


    public List<EmployeeView> getAllEmployees() {
        return employeerepo.findEmployeeByStatus("Active");
    }

    public List<Employee> getEmployeeProfile(String email) {
        return employeerepo.findEmployeeByEmail(email);
    }

    public void deleteEmployee(Employee emp) {
        if(emp.getEmployeeId() == null && emp.getEmail() == null) {
            throw new RuntimeException("Please provide Employee ID/Email to terminate employee profile");
        }else{
            if (emp.getEmail() != null && emp.getEmail() != "" ) {
                List<Employee> curr = employeerepo.findEmployeeByEmail(emp.getEmail());
                if (curr.isEmpty() || curr.get(0).getStatus().equals("Inactive")) {
                    throw new RuntimeException("No Employee found with the given email ID");
                }else{
                    curr.get(0).setStatus("Inactive");
                    employeerepo.save(curr.get(0));
                }
            }else{
                List<Employee> curr = employeerepo.findEmployeeByemployeeId(emp.getEmployeeId());
                if (curr.isEmpty() || curr.get(0).getStatus().equals("Inactive")) {
                    throw new RuntimeException("No Employee found with the given Employee ID");
                }else{
                    curr.get(0).setStatus("Inactive");
                    employeerepo.save(curr.get(0));
                }
            }
        }
    }
}
