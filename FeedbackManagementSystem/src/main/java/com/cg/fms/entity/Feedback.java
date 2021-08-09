package com.cg.fms.entity;

/*****************************************************************
 *          @author          Arpan Pal
 *          Description      It is an entity class for Feedback
 *                           @Entity 
 *         Version             1.0
 *         Created On        22-July-2021
 ******************************************************************/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long feedbackId;
	private String prgId;
	private String trnrId;
	private int feedbackCriteria1;
	private int feedbackCriteria2;
	private int feedbackCriteria3;
	private int feedbackCriteria4;
	private int feedbackCriteria5;
	private String comments;
	private String suggestions;
	
	// Default Constructor
	public Feedback() {
		super();
	}

	// Parameterised Constructor Final
	public Feedback(String prgId, String trnrId, int feedbackCriteria1, int feedbackCriteria2, int feedbackCriteria3,
			int feedbackCriteria4, int feedbackCriteria5, String comments, String suggestions) {
		super();
		this.prgId = prgId;
		this.trnrId = trnrId;
		this.feedbackCriteria1 = feedbackCriteria1;
		this.feedbackCriteria2 = feedbackCriteria2;
		this.feedbackCriteria3 = feedbackCriteria3;
		this.feedbackCriteria4 = feedbackCriteria4;
		this.feedbackCriteria5 = feedbackCriteria5;
		this.comments = comments;
		this.suggestions = suggestions;
	}
	
	// Getters and Setters
	public long getFeedbackId() {
		return feedbackId;
	}

	public String getPrgId() {
		return prgId;
	}

	public void setPrgId(String prgId) {
		this.prgId = prgId;
	}

	public String getTrnrId() {
		return trnrId;
	}

	public void setTrnrId(String trnrId) {
		this.trnrId = trnrId;
	}

	public int getFeedbackCriteria1() {
		return feedbackCriteria1;
	}
	public void setFeedbackCriteria1(int feedbackCriteria1) {
		this.feedbackCriteria1 = feedbackCriteria1;
	}
	public int getFeedbackCriteria2() {
		return feedbackCriteria2;
	}
	public void setFeedbackCriteria2(int feedbackCriteria2) {
		this.feedbackCriteria2 = feedbackCriteria2;
	}
	public int getFeedbackCriteria3() {
		return feedbackCriteria3;
	}
	public void setFeedbackCriteria3(int feedbackCriteria3) {
		this.feedbackCriteria3 = feedbackCriteria3;
	}
	public int getFeedbackCriteria4() {
		return feedbackCriteria4;
	}
	public void setFeedbackCriteria4(int feedbackCriteria4) {
		this.feedbackCriteria4 = feedbackCriteria4;
	}
	public int getFeedbackCriteria5() {
		return feedbackCriteria5;
	}
	public void setFeedbackCriteria5(int feedbackCriteria5) {
		this.feedbackCriteria5 = feedbackCriteria5;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	
	@Override
	public String toString() {
		return "Feedback [feedbackId = " + feedbackId + ", programId = " + prgId + ", trnrId = " + trnrId +
				", feedbackCriteria1 = " + feedbackCriteria1 + ", feedbackCriteria2 = " + feedbackCriteria2 + 
				", feedbackCriteria3 = " + feedbackCriteria3 + ", feedbackCriteria4 = " + feedbackCriteria4 + 
				", feedbackCriteria5 = " + feedbackCriteria5 + ", comments = " + comments + ", suggestions=" + suggestions +"]";
	}
	
}