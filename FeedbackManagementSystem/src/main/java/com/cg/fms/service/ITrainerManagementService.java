package com.cg.fms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Employee;

@Service
public interface ITrainerManagementService {
	
	Employee addTrainer(Employee emp);
	Employee updateTrainer(Employee emp, long empId);
	ResponseEntity <Employee> removeTrainer(long empId);
	Employee viewTrainer(long empId);
	List<Employee> viewAllTrainer();
	public List<Employee> viewAllTrainerBySkill(String Skill);
	
}
