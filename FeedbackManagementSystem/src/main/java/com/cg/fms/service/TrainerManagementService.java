package com.cg.fms.service;

/*****************************************************************
 *          @author          Sneha Chakraborty
 *          Description      It is a service class for Trainer
 *                           @Service 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.repository.ITrainerManagementRepository;

@Service
public class TrainerManagementService {

	@Autowired
	private ITrainerManagementRepository trainermanagementrepository;
	
	public TrainerManagementService(ITrainerManagementRepository trainermanagementrepository) {
		super();
		this.trainermanagementrepository = trainermanagementrepository;
	}
	
	public Trainer addTrainer(Trainer trainer) {
		Optional<Trainer> existingtrainer = this.trainermanagementrepository.findById(trainer.getTrainerId());
		if(existingtrainer.isPresent()) {
			throw new RecordAlreadyExistsException("Trainer with id "+ trainer.getTrainerId()+" already exists");
		}
		return trainermanagementrepository.save(trainer);
	}

	public Trainer updateTrainer(Trainer trainer, String trainerId) {
		Trainer existingtrainer = this.trainermanagementrepository.getById(trainerId);
		existingtrainer.setTrainerName(trainer.getTrainerName());
		existingtrainer.setTrainerPassword(trainer.getTrainerPassword());
		existingtrainer.setSkill(trainer.getSkill());
		return this.trainermanagementrepository.save(existingtrainer);
	}

	public ResponseEntity <Trainer> removeTrainer(String trainerId) {
		Trainer existingtrainer = this.trainermanagementrepository.findById(trainerId)
				.orElseThrow(() -> new UserNotFoundException("Trainer not found with id " + trainerId));
		this.trainermanagementrepository.delete(existingtrainer);
		return ResponseEntity.ok().build();
	}

	public List<Trainer> viewAllTrainer() {
		return this.trainermanagementrepository.findAll();
	}
	
	public Trainer viewTrainerById(String trainerId) {
		return this.trainermanagementrepository.findById(trainerId)
				.orElseThrow(() -> new UserNotFoundException("Trainer not found with id " + trainerId));
	}

	public List <Trainer> viewAllTrainerBySkill(String Skill) {
		List <Trainer> result = trainermanagementrepository.findAllTrainerBySkill(Skill);
		return result;
	}

}