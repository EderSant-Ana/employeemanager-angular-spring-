package com.santana.employeemanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.employeemanager.exception.EmployeeAlreadyRegisteredException;
import com.santana.employeemanager.exception.ResourceNotFoundException;
import com.santana.employeemanager.model.Employee;
import com.santana.employeemanager.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeResource {
	
	private EmployeeService employeeService;
	
	@GetMapping(value = "/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}

	@GetMapping(value = "/employees/{id}")
	public Employee findById(@PathVariable Long id) throws ResourceNotFoundException{
		return employeeService.findById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/employees/add")
	public Employee postEmployee(@RequestBody Employee emp) throws EmployeeAlreadyRegisteredException {
		return employeeService.createEmployee(emp);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/employees/update")
	public Employee putEmployee(@RequestBody Employee emp) throws ResourceNotFoundException  {
		return employeeService.updateEmployee(emp);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/employees/delete/{id}")
	public void deleteById(@PathVariable Long id) throws ResourceNotFoundException {
		employeeService.deleteEmployee(id);
	}
}
