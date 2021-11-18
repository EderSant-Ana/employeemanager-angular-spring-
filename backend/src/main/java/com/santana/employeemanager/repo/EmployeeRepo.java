package com.santana.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santana.employeemanager.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByEmail(String email);

}
