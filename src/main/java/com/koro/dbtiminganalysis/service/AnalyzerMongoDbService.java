package com.koro.dbtiminganalysis.service;

import com.koro.dbtiminganalysis.annotation.RunTimer;
import com.koro.dbtiminganalysis.model.EmployeeDocument;
import com.koro.dbtiminganalysis.repository.EmployeeRepositoryMongoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzerMongoDbService {

    private EmployeeRepositoryMongoDb employeeRepositoryMongoDb;

    @Autowired
    public AnalyzerMongoDbService(EmployeeRepositoryMongoDb employeeRepositoryMongoDb) {
        this.employeeRepositoryMongoDb = employeeRepositoryMongoDb;
    }

    @RunTimer
    public void saveToDb(List<EmployeeDocument> employeeList) {
        System.out.println("MongoDB: Saving to DB...");
        employeeRepositoryMongoDb.saveAll(employeeList);
        System.out.println("MongoDB: Saving to DB finished.");
    }

    @RunTimer
    public List<EmployeeDocument> readFromDb() {
        System.out.println("MongoDB: Reading from DB...");
        List<EmployeeDocument> employeeList = employeeRepositoryMongoDb.findAll();
        System.out.println("MongoDB: Reading from DB finished.");
        return employeeList;
    }
}
