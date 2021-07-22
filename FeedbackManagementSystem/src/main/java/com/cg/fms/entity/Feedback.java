package com.cg.fms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "participant_Sequence")
    @SequenceGenerator(name = "participant_Sequence", sequenceName = "participant_SEQ")
	@Column(name = "participant")
	private Employee participant;
	
	@Column(name = "program")
	private Program program;
	
	@Column(name = "feedbackCriteria1")
	private int feedbackCriteria1;
	
	@Column(name = "feedbackCriteria2")
	private int feedbackCriteria2;
	
	@Column(name = "feedbackCriteria3")
	private int feedbackCriteria3;
	
	@Column(name = "feedbackCriteria4")
	private int feedbackCriteria4;
	
	@Column(name = "feedbackCriteria5")
	private int feedbackCriteria5;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "suggestions")
	private String suggestions;
	
	// Default Constructor
	public Feedback() {
		super();
	}
	
	// Parameterised Constructor 1
	public Feedback(Employee participant, Program program, String comments, String suggestions) {
		super();
		this.participant = participant;
		this.program = program;
		this.comments = comments;
		this.suggestions = suggestions;
	}

	// Parameterised Constructor Final
	public Feedback(Employee participant, Program program, int feedbackCriteria1, int feedbackCriteria2,
			int feedbackCriteria3, int feedbackCriteria4, int feedbackCriteria5, String comments, String suggestions) {
		super();
		this.participant = participant;
		this.program = program;
		this.feedbackCriteria1 = feedbackCriteria1;
		this.feedbackCriteria2 = feedbackCriteria2;
		this.feedbackCriteria3 = feedbackCriteria3;
		this.feedbackCriteria4 = feedbackCriteria4;
		this.feedbackCriteria5 = feedbackCriteria5;
		this.comments = comments;
		this.suggestions = suggestions;
	}
	
	// Getters and Setters
	public Employee getParticipant() {
		return participant;
	}
	public void setParticipant(Employee participant) {
		this.participant = participant;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + feedbackCriteria1;
		result = prime * result + feedbackCriteria2;
		result = prime * result + feedbackCriteria3;
		result = prime * result + feedbackCriteria4;
		result = prime * result + feedbackCriteria5;
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
		result = prime * result + ((suggestions == null) ? 0 : suggestions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (feedbackCriteria1 != other.feedbackCriteria1)
			return false;
		if (feedbackCriteria2 != other.feedbackCriteria2)
			return false;
		if (feedbackCriteria3 != other.feedbackCriteria3)
			return false;
		if (feedbackCriteria4 != other.feedbackCriteria4)
			return false;
		if (feedbackCriteria5 != other.feedbackCriteria5)
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		if (suggestions == null) {
			if (other.suggestions != null)
				return false;
		} else if (!suggestions.equals(other.suggestions))
			return false;
		return true;
	}
	
	
	
}
