package com.cg.fms.repository;

/*****************************************************************
 *          @author          Pushpanjali Kumari
 *          Description      It is a repository interface for Program
 *                           @Repository 
 *         Version             1.0
 *         Created On        28-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Program;

@Repository
public interface ITrainingProgramRepository extends JpaRepository<Program, String> {

	Program findByProgramId(String programId);
	Optional <Program> findByprogramId(String programId);
	public List<Program> findAllProgramsByStartDate(String startDate);
	public List<Program> findAllProgramsBytId(String trainerid);
}

