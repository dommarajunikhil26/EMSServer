package com.nikhil.ManageEmployee.service;

import com.nikhil.ManageEmployee.model.Employee;
import com.nikhil.ManageEmployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<String> addEmployee(Employee employee)  {
        try{
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee added", HttpStatus.CREATED);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>("Failed adding employee", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteEmployee(String id) {
        try{
            employeeRepository.deleteById(Integer.parseInt(id));
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>("Exception Occurred", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> editEmployee(Employee employee) {
        try{
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if(existingEmployee.isPresent()){
                Employee updateEmployee = existingEmployee.get();
                updateEmployee.setFirstName(employee.getFirstName());
                updateEmployee.setLastName(employee.getLastName());
                updateEmployee.setEmail(employee.getEmail());
                updateEmployee.setPhn(employee.getPhn());
                employeeRepository.save(updateEmployee);
                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>("Exception occurred while updating employee", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Employee>> getEmployeeById(String id) {
        return new ResponseEntity<>(employeeRepository.findAllById(Collections.singleton(Integer.parseInt(id))), HttpStatus.OK);
    }
}
