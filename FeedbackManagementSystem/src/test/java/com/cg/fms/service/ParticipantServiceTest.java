package com.cg.fms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;

import com.cg.fms.entity.Participant;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.repository.IParticipantRepository;

class ParticipantServiceTest {

	private ParticipantService participantService;
	
	private IParticipantRepository participantRepo;
	
    @BeforeEach
    void setUp() {
    	participantRepo = mock(IParticipantRepository.class);
    	participantService = new ParticipantService(participantRepo);
    }
	
    @Test
    void shouldSavedParticipantSuccessfully() throws UserNotFoundException {
    	Participant participant = new Participant("12334456","Participant","Participant@1234","passworD@134");
        given(participantRepo.findById(participant.getParticipantId())).willReturn(Optional.empty());
        given(participantRepo.save(participant)).willAnswer(invocation -> invocation.getArgument(0));

        String savedParticipant = participantService.addParticipant(participant.getParticipantName(), participant.getParticipantUserName(), participant.getParticipantPassword(), true);

        assertThat(savedParticipant).isNotNull();

        verify(participantRepo).save(any(Participant.class));
    }
  
}

