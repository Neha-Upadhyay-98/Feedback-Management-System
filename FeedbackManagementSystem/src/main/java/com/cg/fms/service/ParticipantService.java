package com.cg.fms.service;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is a service class for Participant
 *                           @Service
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Participant;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.repository.IParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	private IParticipantRepository participantrepository;
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	public AdminService adminservice;
	
	@Autowired
	public TrainerManagementService trainermanagementservice;
	
	int flag = 0;
	
	public ParticipantService (IParticipantRepository participantrepository) {
		this.participantrepository = participantrepository;
	}
	
	public String participantLogin(String participantUserName, String participantPassword) {
		if(this.loginservice.login(participantUserName, participantPassword, false)=="Logged In") {
			flag = 1;
			return participantUserName + " successfully logged in!";
		}
		else {
			return "Login attempt failed";
		}
	}
	
	public String participantLogout(String participantUserName, String participantPassword) {
		if(this.loginservice.logout(participantUserName, participantPassword)=="Logged Out") {
			flag = 0;
			return participantUserName + " successfully logged out!";
		}
		else {
			return "Logout attempt failed";
		}
	}
	
	public String addParticipant(String participantName, String participantUserName, String participantPassword, boolean test) throws RecordAlreadyExistsException {
		String participantId = Integer.toString(participantUserName.hashCode());
		Participant entry = new Participant(participantId, participantName, participantUserName, participantPassword);
		if(participantrepository.existsById(participantId)) {
			throw new RecordAlreadyExistsException ("Username " + participantUserName + " already exists!");
		}
		else {
			try {
				if(!test) this.loginservice.signup(participantUserName, participantPassword);
				this.participantrepository.save(entry);
				return "Participant added";
			}
			catch(RecordAlreadyExistsException e) {
				return e.getMessage();
			}
		}	
	}

	public Participant updateParticipant(Participant participant, String participantId) {
		if(this.flag==1) {
			Participant existingparticipant = this.participantrepository.findById(participantId).get();
			existingparticipant.setParticipantName(participant.getParticipantName());
			existingparticipant.setParticipantPassword(participant.getParticipantPassword());
			return this.participantrepository.save(existingparticipant);
		}
		else {
			throw new UserNotLoggedInException("Participant not logged in");
		}
	}

	public String removeParticipant(String participantId) {
		if(this.flag==1 || this.adminservice.flag==1 || this.trainermanagementservice.flag==1) {
			Participant existingparticipant = this.participantrepository.getById(participantId);
			this.loginservice.deleteAccount(participantId);
			this.participantrepository.delete(existingparticipant);
			return "Participant with id " + participantId + "deleted";
		}
		else {
			throw new UserNotLoggedInException("Participant or Admin or Trainer not logged in");
		}
	}

	public Participant viewParticipantById(String participantId) {
		if(this.adminservice.flag==1 || this.trainermanagementservice.flag==1) {
			return this.participantrepository.findById(participantId)
					.orElseThrow(() -> new UserNotFoundException("Participant not found with id " + participantId));
		}
		else {
			throw new UserNotLoggedInException("Admin or Trainer not logged in");
		}
	}
	
	public List<Participant> viewAllParticipants() {
		if(this.adminservice.flag==1 || this.trainermanagementservice.flag==1) {
			return this.participantrepository.findAll();
		}
		else {
			throw new UserNotLoggedInException("Participant not logged in");
		}
	}

}