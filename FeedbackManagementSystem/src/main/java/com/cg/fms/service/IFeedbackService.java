package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;

@Service
public interface IFeedbackService {

	public Feedback addFeedback(Feedback fdb);
	public Feedback updateFeedback(Feedback fdb, long empId);
	
	public List<Feedback> viewTrainerFeedback(List<Feedback> feedbackList, long trainerid);
	public List<Feedback> viewProgramFeedback(List<Feedback> feedbackList, Program program);

}
