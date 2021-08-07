package com.cg.fms.repository;

/*******************************************************************
 *          @author          Sneha Chakraborty
 *          Description      It is a repository interface for Trainer
 *                           @Repository 
 *         Version             1.0
 *         Created On        27-July-2021
 *******************************************************************/

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Trainer;

@Repository
public interface ITrainerManagementRepository extends JpaRepository<Trainer, String>{

	public List <Trainer> findAllTrainerBySkill(String skill);
	
}