package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Employee;
import com.cg.fms.exception.EmployeeNotFoundException;
import com.cg.fms.repository.ITrainerManagementRepository;

@Service
public class TrainerManagementService implements ITrainerManagementService {

	@Autowired
	private ITrainerManagementRepository trainermanagementrepository;
	
	@Override
	public Employee addTrainer(Employee emp) {
		return trainermanagementrepository.save(emp);
	}

	@Override
	public Employee updateTrainer(Employee emp, long empId) {
		Employee existingtrainer = this.trainermanagementrepository.getById(emp.getEmployeeId());
		existingtrainer.setEmpName(emp.getEmpName());
		existingtrainer.setPassword(emp.getPassword());
		return this.trainermanagementrepository.save(existingtrainer);
	}

	@Override
	public ResponseEntity <Employee> removeTrainer(long empId) {
		Employee existingtrainer = this.trainermanagementrepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Trainer not found with id " + empId));
		this.trainermanagementrepository.delete(existingtrainer);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<Employee> viewAllTrainer() {
		return this.trainermanagementrepository.findAll();
	}
	
	@Override
	public Employee viewTrainer(long empId) {
		return this.trainermanagementrepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Trainer not found with id " + empId));
	}

	@Override
	public List<Employee> viewAllTrainerBySkill(String Skill) {
		return this.trainermanagementrepository.viewAllTrainerBySkill(Skill);
	}

}
