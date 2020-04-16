package com.koro.dbtiminganalysis;

import com.koro.dbtiminganalysis.model.Employee;
import com.koro.dbtiminganalysis.model.EmployeeDocument;
import com.koro.dbtiminganalysis.service.AnalyzerJDBCService;
import com.koro.dbtiminganalysis.service.AnalyzerMongoDbService;
import com.koro.dbtiminganalysis.service.AnalyzerService;
import com.koro.dbtiminganalysis.service.CsvFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Analyzer {

    private AnalyzerService analyzerService;
    private AnalyzerJDBCService analyzerJDBCService;
    private CsvFileService csvFileService;
    private AnalyzerMongoDbService analyzerMongoDbService;

    @Autowired
    public Analyzer(AnalyzerService analyzerService,
                    AnalyzerJDBCService analyzerJDBCService,
                    CsvFileService csvFileService,
                    AnalyzerMongoDbService analyzerMongoDbService
    )

    {
        this.analyzerService = analyzerService;
        this.analyzerJDBCService = analyzerJDBCService;
        this.csvFileService = csvFileService;
        this.analyzerMongoDbService = analyzerMongoDbService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        List<Employee> employeeList = csvFileService.readFromCsvFile(Employee.class);
        List<EmployeeDocument> employeeDocumentList = csvFileService.readFromCsvFile(EmployeeDocument.class);

        //remotemysql.com with Hiberante
        analyzerService.saveToDb(employeeList);
        analyzerService.readFromDb();

        //remotemysql.com with JDBC
//        analyzerJDBCService.saveToDb(employeeList);
//        analyzerJDBCService.readFromDb();

        //MongoDB
//        analyzerMongoDbService.saveToDb(employeeDocumentList);
//        analyzerMongoDbService.readFromDb();
    }
}
