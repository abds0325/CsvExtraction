package com.abds.csv.service;

import com.abds.csv.entity.Employee;
import com.abds.csv.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> findAll(){
        return (List<Employee>) employeeRepository.findAll();
    }
}
