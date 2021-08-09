package com.cg.fms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.ProgramNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ITrainingProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TrainingProgramServiceTest {

	private TrainingProgramService trainingProgramService;
    private ITrainingProgramRepository trainingProgramRepository;
    private List<Feedback> feedbacklist= new ArrayList<Feedback>();
    private List<Course> courselist= new ArrayList<Course>();
    
	
	@BeforeEach
	void setUp() throws Exception {
		trainingProgramRepository = mock(ITrainingProgramRepository.class);
    	trainingProgramService = new TrainingProgramService(trainingProgramRepository);
	}

	@Test
    void shouldSaveProgramSuccessfully() throws ProgramNotFoundException {
		Feedback feedback=new Feedback("P001","TR001",1,2,3,4,5,"Good","FAQ");
    	feedbacklist.add(feedback);
    	Course course = new Course("CS001","Java", "Programming", "25", "PR001");
    	courselist.add(course);
		
    	Program program = new Program("PR001", "ProgramName1", "2021-05-01","2021-05-30", "T001", courselist, feedbacklist);
        given(trainingProgramRepository.findById(program.getProgramId())).willReturn(Optional.empty());
        given(trainingProgramRepository.save(program)).willAnswer(invocation -> invocation.getArgument(0));

        Program savedProgram = trainingProgramService.createProgram(program, true);

        assertThat(savedProgram).isNotNull();

        verify(trainingProgramRepository).save(any(Program.class));
    }
	
    @Test
    void shouldThrowErrorWhenSaveProgramWithExistingId() {
    	Feedback feedback=new Feedback("P001","TR001",1,2,3,4,5,"Good","FAQ");
    	feedbacklist.add(feedback);
    	Course course = new Course("CS001","Java", "Programming", "25", "PR001");
    	courselist.add(course);
		
    	Program program = new Program("PR001", "ProgramName1", "2021-05-01","2021-05-30", "T001", courselist, feedbacklist);
        given(trainingProgramRepository.findById(program.getProgramId())).willReturn(Optional.of(program));

        assertThrows(RecordAlreadyExistsException.class, () -> {
        	trainingProgramService.createProgram(program, true);
        });

        verify(trainingProgramRepository, never()).save(any(Program.class));
    }
}
