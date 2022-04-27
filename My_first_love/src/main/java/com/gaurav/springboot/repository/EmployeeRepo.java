package com.gaurav.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurav.springboot.model.Employee;
 
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
