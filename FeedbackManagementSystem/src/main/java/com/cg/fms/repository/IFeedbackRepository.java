package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long>{

	public Feedback addFeedback(Feedback fdb);
	public Feedback updateFeedback(Feedback fdb, long empId);
	

	public List<Feedback> viewTrainerFeedback(List<Feedback> feedbackList, long trainerid);
	public List<Feedback> viewProgramFeedback(List<Feedback> feedbackList, Program program);

}
