package com.cg.fms.service;

/*****************************************************************
 *          @author          Arpan Pal
 *          Description      It is a service class for Feedback
 *                           @Service 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Feedback;
import com.cg.fms.repository.IFeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	IFeedbackRepository feedbackrepository;
	
	public FeedbackService(IFeedbackRepository feedbackrepository) {
		super();
		this.feedbackrepository = feedbackrepository;
	}
	
	public Feedback addFeedback(Feedback fdb) {
		return feedbackrepository.save(fdb);
	}

	public Feedback updateFeedback(Feedback fdb, long feedbackId) {
		Feedback result = this.feedbackrepository.getById(feedbackId);
		result.setComments(fdb.getComments());
		result.setFeedbackCriteria1(fdb.getFeedbackCriteria1());
		result.setFeedbackCriteria2(fdb.getFeedbackCriteria2());
		result.setFeedbackCriteria3(fdb.getFeedbackCriteria3());
		result.setFeedbackCriteria4(fdb.getFeedbackCriteria4());
		result.setFeedbackCriteria5(fdb.getFeedbackCriteria5());
		result.setSuggestions(fdb.getSuggestions());
		return this.feedbackrepository.save(result);
	}
	
	public Feedback findFeedbackById(Long id) {
    	return feedbackrepository.getById(id);
	}
	
	public List<Feedback> viewFeedbackByTrainerId(String trnrid) {
		return feedbackrepository.findFeedbackByTrnrId(trnrid);
	}

	public List<Feedback> viewFeedbackByProgramId(String prgid) {
		return feedbackrepository.findFeedbackByPrgId(prgid);
	}

}