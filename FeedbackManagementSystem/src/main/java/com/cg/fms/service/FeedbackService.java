package com.cg.fms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;
import com.cg.fms.repository.IFeedbackRepository;

public class FeedbackService implements IFeedbackService {

	@Autowired
	private IFeedbackRepository feedbackrepository;
	
	@Override
	public Feedback addFeedback(Feedback fdb) {
		return this.feedbackrepository.save(fdb);
	}

	@Override
	public Feedback updateFeedback(Feedback fdb, long empId) {
		Feedback existingFeedback = this.feedbackrepository.getById(empId);
		existingFeedback.setProgram(fdb.getProgram());
		existingFeedback.setFeedbackCriteria1(fdb.getFeedbackCriteria1());
		existingFeedback.setFeedbackCriteria2(fdb.getFeedbackCriteria2());
		existingFeedback.setFeedbackCriteria3(fdb.getFeedbackCriteria3());
		existingFeedback.setFeedbackCriteria4(fdb.getFeedbackCriteria4());
		existingFeedback.setFeedbackCriteria5(fdb.getFeedbackCriteria5());
		existingFeedback.setProgram(fdb.getProgram());
		existingFeedback.setComments(fdb.getComments());
		return this.feedbackrepository.save(existingFeedback);
	}

	@Override
	public List<Feedback> viewTrainerFeedback(List<Feedback> feedbackList, long trainerid) {
	
		List<Feedback> results = new ArrayList<Feedback>();
		for (Feedback fdb : feedbackList) {
			if(fdb.getParticipant().getEmployeeId() == trainerid)
				results.add(fdb);
		}
		return results;
	}

	@Override
	public List<Feedback> viewProgramFeedback(List<Feedback> feedbackList, Program program) {
		
		List<Feedback> results = new ArrayList<Feedback>();
		for (Feedback fdb : feedbackList) 
			if(fdb.getProgram() == program)
				results.add(fdb);
		return results;
	}

}
