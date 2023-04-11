package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Async
    public List<Employee> getAllList(){
        return employeeRepository.findAll();
    }
    @Async
    public void delete(String id){
        employeeRepository.delete(Long.valueOf(id));
    }
    @Async
    public void saveNew(){
        Employee employee = new Employee();
        employeeRepository.save(employee);
    }
    @Async
    public Employee getById(String id){
        return employeeRepository.getByID(Long.valueOf(id));
    }
    @Async
    public Employee update(Employee employee){
        return employeeRepository.update(employee);
    }
    @Async
    public void switchStatus(String id){
        Employee employee = employeeRepository.getByID(Long.parseLong(id));
        employee.setStatus(!employee.getStatus());
        employeeRepository.update(employee);
    }
}
