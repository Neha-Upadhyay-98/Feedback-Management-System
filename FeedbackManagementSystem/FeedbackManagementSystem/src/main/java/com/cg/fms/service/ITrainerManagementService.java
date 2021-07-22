package com.cg.fms.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Employee;
@Service
public interface ITrainerManagementService {
	
	public Employee addTrainer(Employee emp);
	public Employee updateTrainer(Employee emp);
	public ResponseEntity<Employee> removeTrainer(Employee emp);
	public Employee viewTrainer(Employee emp);
	public List<Employee> viewAllTrainer(String Skill);
	public List<Employee> viewAllTrainer();
	
	
}
