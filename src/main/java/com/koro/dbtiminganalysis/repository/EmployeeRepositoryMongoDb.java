package com.koro.dbtiminganalysis.repository;

import com.koro.dbtiminganalysis.model.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryMongoDb extends MongoRepository<EmployeeDocument, String> {
}
