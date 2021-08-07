package com.cg.fms.repository;

/*********************************************************************
 *          @author          Arpan Pal
 *          Description      It is a repository interface for Feedback
 *                           @Repository 
 *         Version             1.0
 *         Created On        27-July-2021
 ********************************************************************/

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Feedback;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
	
	public List<Feedback> findFeedbackByTrnrId(String trnrid);
	public List<Feedback> findFeedbackByPrgId(String prgid);

}