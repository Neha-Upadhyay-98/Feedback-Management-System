package com.cg.fms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.fms.entity.Feedback;
import com.cg.fms.exception.FeedbackNotFoundException;
import com.cg.fms.repository.IFeedbackRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {
	
	private FeedbackService feedbackService;
	private IFeedbackRepository feedbackRepo;

	@BeforeEach
    void setUp() {
		feedbackRepo = mock(IFeedbackRepository.class);
		feedbackService = new FeedbackService(feedbackRepo);
    }
	
	@Test
    void shouldCreateFeedbackSuccessfully() throws FeedbackNotFoundException {
		
		Feedback fdb = new Feedback("P1001", "T501", 9, 8, 6, 7, 8, "Good understanding", "FAQ missing");
        given(feedbackRepo.findById(fdb.getFeedbackId())).willReturn(Optional.empty());
        given(feedbackRepo.save(fdb)).willAnswer(invocation -> invocation.getArgument(0));

        Feedback savedFeedback = feedbackService.addFeedback(fdb, true);

        assertThat(savedFeedback).isNotNull();

        verify(feedbackRepo).save(any(Feedback.class));
	}

}

