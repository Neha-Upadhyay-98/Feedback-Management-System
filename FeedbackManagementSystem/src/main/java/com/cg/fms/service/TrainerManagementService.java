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
import org.springframework.stereotype.Service;
import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.repository.ITrainerManagementRepository;

@Service
public class TrainerManagementService {

	@Autowired
	private ITrainerManagementRepository trainermanagementrepository;
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	public AdminService adminservice;
	
	int flag = 0;
	
	public TrainerManagementService(ITrainerManagementRepository trainermanagementrepository) {
		super();
		this.trainermanagementrepository = trainermanagementrepository;
	}
	
	public String trainerLogin(String trainerUserName, String trainerPassword) {
		if(this.loginservice.login(trainerUserName, trainerPassword, false)=="Logged In") {
			flag = 1;
			return trainerUserName + " successfully logged in!";
		}
		else {
			return "Login attempt failed";
		}
	}
	
	public String trainerLogout(String trainerUserName, String trainerPassword) {
		if(this.loginservice.logout(trainerUserName, trainerPassword)=="Logged Out") {
			flag = 0;
			return trainerUserName + " successfully logged out!";
		}
		else {
			return "Logout attempt failed";
		}
	}
	
	public Trainer addTrainer(Trainer trainer, boolean test) {
		if(test || this.adminservice.flag==1) {
			Optional<Trainer> existingtrainer = this.trainermanagementrepository.findById(trainer.getTrainerId());
			if(existingtrainer.isPresent()) {
				throw new RecordAlreadyExistsException("Trainer with id "+ trainer.getTrainerId()+" already exists");
			}
			if(!test) this.loginservice.signup(trainer.getTrainerUserName(), trainer.getTrainerPassword());
			return trainermanagementrepository.save(trainer);
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public Trainer updateTrainer(Trainer trainer, String trainerId) {
		if(this.adminservice.flag==1) {
			Trainer existingtrainer = this.trainermanagementrepository.getById(trainerId);
			existingtrainer.setTrainerName(trainer.getTrainerName());
			existingtrainer.setTrainerUserName(trainer.getTrainerUserName());
			existingtrainer.setTrainerPassword(trainer.getTrainerPassword());
			existingtrainer.setSkill(trainer.getSkill());
			return this.trainermanagementrepository.save(existingtrainer);
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public String removeTrainer(String trainerId) {
		if(this.adminservice.flag==1) {
			Trainer existingtrainer = this.trainermanagementrepository.findById(trainerId)
					.orElseThrow(() -> new UserNotFoundException("Trainer not found with id " + trainerId));
			this.loginservice.deleteAccount(trainerId);
			this.trainermanagementrepository.delete(existingtrainer);
			return "Trainer with id " + trainerId + "deleted";
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public List<Trainer> viewAllTrainer() {
		if(this.adminservice.flag==1) {
			return this.trainermanagementrepository.findAll();
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}
	
	public Trainer viewTrainerById(String trainerId) {
		if(this.adminservice.flag==1) {
			return this.trainermanagementrepository.findById(trainerId)
					.orElseThrow(() -> new UserNotFoundException("Trainer not found with id " + trainerId));
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public List <Trainer> viewAllTrainerBySkill(String Skill) {
		if(this.adminservice.flag==1) {
			List <Trainer> result = trainermanagementrepository.findAllTrainerBySkill(Skill);
			return result;
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

}