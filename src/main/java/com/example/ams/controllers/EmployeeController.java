package com.example.ams.controllers;

import com.example.ams.models.Employee;
import com.example.ams.services.EmployeeService;
import com.example.ams.views.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/admin/employees")
 public class EmployeeController {

    EmployeeService empservice;

    @Autowired
    public EmployeeController(EmployeeService emp) {
        this.empservice = emp;
    }

    @GetMapping
    public List<EmployeeView> getAllEmployees(){
        return empservice.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<HashMap<String,String>> createEmployee(@RequestBody Employee emp) {
        try {
            empservice.createEmployee(emp);
            return  ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>(Map.of(
                    "status", "1",
                    "message", "Employee created Successfully"
            )));
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(Map.of(
                    "status", "0",
                    "message", e.getMessage()
            )));
        }
    }

    @PutMapping
    public ResponseEntity<HashMap<String, String>> updateEmployee(@RequestBody Employee emp) {
        try {
            empservice.updateEmployee(emp);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(Map.of(
                    "status", "1",
                    "message", "Employee updated Successfully"
            )));
        } catch (Exception e ) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(Map.of(
                    "status", "0",
                    "message", e.getMessage()
            )));
        }
    }

    @DeleteMapping
    public ResponseEntity<HashMap<String , String>> separateEmployee(@RequestBody Employee emp) {
        try {
            empservice.deleteEmployee(emp);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new HashMap<String, String>(Map.of(
                    "status", "1",
                    "message", "Employee separated Successfully"
            )));
        } catch (Exception e ) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(Map.of(
                    "status", "0",
                    "message", e.getMessage()
            )));
        }
    }
}
