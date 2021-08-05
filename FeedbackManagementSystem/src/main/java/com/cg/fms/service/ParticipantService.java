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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Participant;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.repository.IParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	private IParticipantRepository participantrepository;
	
	public ParticipantService (IParticipantRepository participantrepository) {
		this.participantrepository = participantrepository;
	}
	
	public String addParticipant(String participantName, String participantUserName, String participantPassword) throws RecordAlreadyExistsException {
		String participantId = Integer.toString(participantUserName.hashCode());
		Participant entry = new Participant(participantId, participantName, participantUserName, participantPassword);
		if(participantrepository.existsById(participantId)) {
			throw new RecordAlreadyExistsException ("Username " + participantUserName + " already exists!");
		}
		else {
			this.participantrepository.save(entry);
			return "Participant added";
		}	
	}

	public Participant addParticipant(Participant admin) {
		return this.participantrepository.save(admin);
	}

	public Participant updateParticipant(Participant participant, String participantId) {
		Participant existingparticipant = this.participantrepository.findById(participantId).get();
		existingparticipant.setParticipantName(participant.getParticipantName());
		existingparticipant.setParticipantPassword(participant.getParticipantPassword());
		return this.participantrepository.save(existingparticipant);
	}

	public ResponseEntity<Participant> removeParticipant(String participantId) {
		Participant existingparticipant = this.participantrepository.getById(participantId);
		this.participantrepository.delete(existingparticipant);
		return ResponseEntity.ok().build();
	}

	public Participant viewParticipantById(String participantId) {
		return this.participantrepository.findById(participantId)
				.orElseThrow(() -> new UserNotFoundException("Participant not found with id " + participantId));
	}
	
	public List<Participant> viewAllParticipants() {
		return this.participantrepository.findAll();
	}

}