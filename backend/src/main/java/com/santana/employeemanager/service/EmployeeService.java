package com.santana.employeemanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.employeemanager.exception.EmployeeAlreadyRegisteredException;
import com.santana.employeemanager.exception.ResourceNotFoundException;
import com.santana.employeemanager.model.Employee;
import com.santana.employeemanager.repo.EmployeeRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {
	
	private EmployeeRepo employeeRepo;
	
	public List<Employee> findAll(){
		return employeeRepo.findAll();
	}
	
	public Employee findById(Long id) throws ResourceNotFoundException {
		return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				String.format("Employee with id %s not found in the system", id)));
	}
	
	public Employee createEmployee(Employee emp) throws EmployeeAlreadyRegisteredException {
		Employee employee = employeeRepo.findByEmail(emp.getEmail());
		
		if(employee == null) {
			emp.setEmployeeCode(UUID.randomUUID().toString());
			return employeeRepo.save(emp);
		}
		throw new EmployeeAlreadyRegisteredException(
				String.format("Employee with email %s is already registered in the system", emp.getEmail()));
	}
	
	public Employee updateEmployee(Employee emp) throws ResourceNotFoundException {
		Employee employee = employeeRepo.findByEmail(emp.getEmail());
		
		System.out.println(employee);
		
		if(employee != null) {
			employee.setName(emp.getName());
			employee.setEmail(emp.getEmail());
			employee.setJobTitle(emp.getJobTitle());
			employee.setPhone(emp.getPhone());
			employee.setImageUrl(emp.getImageUrl());
			return employeeRepo.save(emp);			
		}
		throw new ResourceNotFoundException(
				String.format("Employee with email %s is not found in the system", emp.getEmail()));		
	}

	public void deleteEmployee(Long id) throws ResourceNotFoundException {
		findById(id);
		employeeRepo.deleteById(id);
	}
}
