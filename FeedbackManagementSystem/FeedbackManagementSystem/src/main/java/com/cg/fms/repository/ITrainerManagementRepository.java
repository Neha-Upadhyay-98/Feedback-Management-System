package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Employee;

@Repository
public interface ITrainerManagementRepository extends JpaRepository<Employee, Integer>{

	public Employee addTrainer(Employee emp);
	public Employee updateTrainer(Employee emp);
	public Employee removeTrainer(Employee emp);
	public Employee viewTrainer(Employee emp);
	public List<Employee> viewAllTrainer(String Skill);
	public List<Employee> viewAllTrainer();
	
	
	
	
}
