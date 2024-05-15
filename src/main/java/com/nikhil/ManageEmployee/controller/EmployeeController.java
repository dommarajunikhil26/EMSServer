package com.nikhil.ManageEmployee.controller;

import com.nikhil.ManageEmployee.model.Employee;
import com.nikhil.ManageEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable String id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee)  {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editEmployee(@RequestBody Employee employee){
        return employeeService.editEmployee(employee);
    }
}
