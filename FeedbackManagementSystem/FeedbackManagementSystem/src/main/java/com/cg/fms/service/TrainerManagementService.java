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
		// TODO Auto-generated method stub
		return trainermanagementrepository.save(emp);
	}

	@Override
	public Employee updateTrainer(Employee emp) {
		// TODO Auto-generated method stub
		Employee existingcourse = this.trainermanagementrepository.getById(emp.getEmployeeId());
		existingcourse.setEmpName(emp.getEmpName());
		existingcourse.setPassword(emp.getPassword());
		
		return null;
	}

	@Override
	public ResponseEntity<Employee> removeTrainer(Employee emp) {
		// TODO Auto-generated method stub
		
			Employee existingcourse = this.trainermanagementrepository.findById(emp.getEmployeeId())
					.orElseThrow(() -> new EmployeeNotFoundException("Course not found with id " + emp.getEmployeeId()));
			this.trainermanagementrepository.delete(existingcourse);
			return ResponseEntity.ok().build();
		}
		
	

	@Override
	public Employee viewTrainer(Employee emp) {
		// TODO Auto-generated method stub
		return this.trainermanagementrepository.findById(emp.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + emp.getEmployeeId()));

	}

	@Override
	public List<Employee> viewAllTrainer(String Skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> viewAllTrainer() {
		// TODO Auto-generated method stub
		return this.trainermanagementrepository.findAll();
	}

}
