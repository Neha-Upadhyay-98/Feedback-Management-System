package com.cg.fms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Trainer;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ITrainerManagementRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TrainerManagementServiceTest {

	private TrainerManagementService trainermanagementservice;
    private ITrainerManagementRepository trainermanagementrepository;
    private List<Feedback> feedbacklist= new ArrayList<Feedback>();
	
	@BeforeEach
	void setUp() throws Exception {
		trainermanagementrepository = mock(ITrainerManagementRepository.class);
    	trainermanagementservice = new TrainerManagementService (trainermanagementrepository);
	}

	@Test
    void shouldThrowErrorWhenSaveTrainerWithExistingId() {
    	Feedback feedback=new Feedback("P001","TR001",1,2,3,4,5,null,null);
    	feedbacklist.add(feedback);
    	Trainer trainer = new Trainer("TR001", "Teacher","Trainer@123","1teacher@#","FullStack", feedbacklist);
        given(trainermanagementrepository.findById(trainer.getTrainerId())).willReturn(Optional.of(trainer));

        assertThrows(RecordAlreadyExistsException.class, () -> {
        	trainermanagementservice.addTrainer(trainer, true);
        });

        verify( trainermanagementrepository, never()).save(any(Trainer.class));
    }

}
