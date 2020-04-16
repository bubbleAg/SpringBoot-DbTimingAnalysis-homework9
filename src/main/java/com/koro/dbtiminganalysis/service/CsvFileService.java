package com.koro.dbtiminganalysis.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.koro.dbtiminganalysis.model.Employee;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CsvFileService {

    private static final String FILE_PATH_NAME = "src/main/resources/MOCK_DATA1.csv";

    public List readFromCsvFile(Class classList) {
        System.out.println("Reading from CSV file...");
        File csvFile = new File(FILE_PATH_NAME);
        List employeeList = null;
        try {
            MappingIterator<Employee> employeeIterator = new CsvMapper()
                    .readerWithTypedSchemaFor(classList)
                    .readValues(csvFile);

            employeeList = employeeIterator.readAll();
            System.out.println("Reading from CSV file finished.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
