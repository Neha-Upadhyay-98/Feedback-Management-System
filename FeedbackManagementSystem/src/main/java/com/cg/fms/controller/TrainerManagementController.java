package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Employee;
import com.cg.fms.service.ITrainerManagementService;

@RestController
@RequestMapping("/api/trainer")
public class TrainerManagementController {
	
	@Autowired
	public ITrainerManagementService trainermanagementservice;
	
	@PostMapping("/addtrainer")
	public Employee addTrainer(@RequestBody Employee emp) {
		return this.trainermanagementservice.addTrainer(emp);
	}
	
	@PutMapping("/updatetrainer/{id}")
	public Employee updateTrainer(@RequestBody Employee emp, @PathVariable("id") long empId) {
		return this.updateTrainer(emp, empId);
	}
	
	@DeleteMapping("/deletetrainer/{id}")
	public ResponseEntity <Employee> removeTrainer(@PathVariable("id") long empId) {
		return this.trainermanagementservice.removeTrainer(empId);
	}
	
	@GetMapping("/viewalltrainer")
	public List<Employee> viewAllTrainer() {
		return this.trainermanagementservice.viewAllTrainer();
	}
	
	@GetMapping("/viewtrainerbyid/{id}")
	public Employee viewTrainer(@PathVariable("id") long empId) {
		return this.trainermanagementservice.viewTrainer(empId);
	}
	
	@GetMapping("/viewalltrainerbyskill/{skill}")
	public List<Employee> viewAllTrainerBySkill(@PathVariable("skill")String skill){
		return this.trainermanagementservice.viewAllTrainerBySkill(skill);
	}

}
