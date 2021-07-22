package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Employee;

@Repository
public interface ITrainerManagementRepository extends JpaRepository<Employee, Long>{

	public List <Employee> viewAllTrainerBySkill(String skill);
	
}
