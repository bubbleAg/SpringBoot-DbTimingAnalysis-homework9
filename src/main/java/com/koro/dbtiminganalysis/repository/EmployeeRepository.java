package com.koro.dbtiminganalysis.repository;

import com.koro.dbtiminganalysis.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
