package com.cg.fms.service;

/*****************************************************************
 *          @author          Pushpanjali Kumari
 *          Description      It is a service class for Program
 *                           @Service 
 *         Version             1.0
 *         Created On        28-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Program;
import com.cg.fms.exception.ProgramNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class TrainingProgramService {

	@Autowired
	private ITrainingProgramRepository programrepository;
	
	@Autowired
	public TrainerManagementService trainermanagementservice;
	
	public TrainingProgramService(ITrainingProgramRepository trainingprogramrepository) {
		super();
		this.programrepository = trainingprogramrepository;
	}
	
	public Program createProgram(Program pr, boolean test) {
		if(test || this.trainermanagementservice.flag==1) {
			Optional<Program> existingProgram = this.programrepository.findById(pr.getProgramId());
			if(existingProgram.isPresent()) {
				throw new RecordAlreadyExistsException("Program with id "+ pr.getProgramId()+" already exists");
			}
			return this.programrepository.save(pr);
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
		
	}

	public Program updateProgram(Program pr,String programId) {
		if(this.trainermanagementservice.flag==1) {
			Program existingProgram = this.programrepository.getById(programId);
			existingProgram.setProgramId(pr.getProgramId());
			existingProgram.setProgramName(pr.getProgramName());
			existingProgram.setStartDate(pr.getStartDate());
			existingProgram.setEndDate(pr.getEndDate());
			existingProgram.settId(pr.gettId());
			return this.programrepository.save(existingProgram);
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}

	public String removeProgram(String programId) {
		if(this.trainermanagementservice.flag==1) {
			Program existingProgram = this.programrepository.findById(programId)
					.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
			this.programrepository.delete(existingProgram);
			return "Program with id " + programId + "deleted";
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}

	public Program viewProgramById(String programId) {
		if(this.trainermanagementservice.flag==1) {
			return this.programrepository.findById(programId)
					.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}
	
	public List<Program> viewAllPrograms() {
		if(this.trainermanagementservice.flag==1) {
			return this.programrepository.findAll();
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}

	public List<Program> viewAllProgramsByDate(String startDate) {
		if(this.trainermanagementservice.flag==1) {
			return this.programrepository.findAllProgramsByStartDate(startDate);
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}

	public List<Program> viewAllProgramsByTrainer(String trainerid) {
		if(this.trainermanagementservice.flag==1) {
			return this.programrepository.findAllProgramsBytId(trainerid);
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}
}
