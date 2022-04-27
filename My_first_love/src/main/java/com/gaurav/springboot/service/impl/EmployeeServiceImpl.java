package com.gaurav.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gaurav.springboot.exception.ResourceNotFoundException;
import com.gaurav.springboot.model.Employee;
import com.gaurav.springboot.repository.EmployeeRepo;
import com.gaurav.springboot.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepo employeeRepo;

	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
	     Optional<Employee> employee=employeeRepo.findById(id);
	     if(employee.isPresent()) {
	    	return employee.get(); 
	     }
	     else {
	    	 throw new ResourceNotFoundException("Employee", "Id", id);
	     }
	     //return employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
	               
		// check if the user exist with given id
		Employee exisitingEmployee=employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","id",id));
		exisitingEmployee.setFname(employee.getFname());
		exisitingEmployee.setLname(employee.getLname());
		exisitingEmployee.setEmail(employee.getEmail());
		// save the object 
		employeeRepo.save(exisitingEmployee);
		return exisitingEmployee;
		
		
	}

	@Override
	public void deleteEmployee(long id) {
		//check if the user exists
		employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		employeeRepo.deleteById(id);
		
	}
	

}
