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
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.repository.IFeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	IFeedbackRepository feedbackrepository;
	
	@Autowired
	public AdminService adminservice;
	
	@Autowired
	public TrainerManagementService trainermanagementservice;
	
	@Autowired
	public ParticipantService partcipantservice;
	
	public FeedbackService(IFeedbackRepository feedbackrepository) {
		super();
		this.feedbackrepository = feedbackrepository;
	}
	
	public Feedback addFeedback(Feedback fdb, boolean test) {
		if(test || this.partcipantservice.flag==1) {
			return feedbackrepository.save(fdb);
		}
		else {
			throw new UserNotLoggedInException("Participant not logged in");
		}
		
	}

	public Feedback updateFeedback(Feedback fdb, long feedbackId) {
		if(this.partcipantservice.flag==1) {
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
		else {
			throw new UserNotLoggedInException("Participant not logged in");
		}
	}
	
	public Feedback findFeedbackById(Long id) {
		if(this.trainermanagementservice.flag==1 || this.adminservice.flag==1) {
			return feedbackrepository.getById(id);
		}
		else {
			throw new UserNotLoggedInException("Admin or Trainer not logged in");
		}
	}
	
	public List<Feedback> viewFeedbackByTrainerId(String trnrid) {
		if(this.adminservice.flag==1) {
			return feedbackrepository.findFeedbackByTrnrId(trnrid);
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public List<Feedback> viewFeedbackByProgramId(String prgid) {
		if(this.trainermanagementservice.flag==1) {
			return feedbackrepository.findFeedbackByPrgId(prgid);
		}
		else {
			throw new UserNotLoggedInException("Trainer not logged in");
		}
	}

}