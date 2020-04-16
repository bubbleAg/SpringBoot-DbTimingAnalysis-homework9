package com.koro.dbtiminganalysis.service;

import com.koro.dbtiminganalysis.annotation.RunTimer;
import com.koro.dbtiminganalysis.model.Employee;
import com.koro.dbtiminganalysis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzerService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public AnalyzerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RunTimer
    public void saveToDb(List<Employee> employeeList) {
        System.out.println("Saving to DB...");
        employeeRepository.saveAll(employeeList);
        System.out.println("Saving to DB finished.");
    }

    @RunTimer
    public List<Employee> readFromDb() {
        System.out.println("Reading from DB...");
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("Reading from DB finished.");
        return employeeList;
    }
}
