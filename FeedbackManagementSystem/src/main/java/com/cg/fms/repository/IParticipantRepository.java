package com.cg.fms.repository;

/************************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is a repository interface for Participant
 *                           @Repository 
 *         Version             1.0
 *         Created On        27-July-2021
 ************************************************************************/

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Participant;

@Repository
public interface IParticipantRepository extends JpaRepository<Participant, String>{
	
	Participant findByParticipantUserName(String participantUserName);
	Optional <Participant> findByparticipantUserName (String participantUserName);
	
}