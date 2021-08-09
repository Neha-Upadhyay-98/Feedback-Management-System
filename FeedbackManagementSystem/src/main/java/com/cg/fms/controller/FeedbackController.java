package com.cg.fms.controller;

/*****************************************************************
 *          @author          Arpan Pal
 *          Description      It is an controller class for Feedback
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 ******************************************************************/

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Feedback;
import com.cg.fms.service.FeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/fms/feedback")
@Api(value = "Feedback Management System",description = "Operations pertaining to Feedback of Feedback Management System")
@Validated
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public FeedbackService feedbackservice;
	
	@ApiOperation(value = "Add a Feedback")
	@PostMapping("/add")
	public Feedback addFeedback(@ApiParam(value = "feedback object stored in database table", required = true)@Valid @RequestBody Feedback feedback) {
		logger.trace("addFeedback method in feedback controller accessed");
		return this.feedbackservice.addFeedback(feedback, false);
	}
	
	@ApiOperation(value = "Update a Feedback")
	@PutMapping("/update/{id}")
	public Feedback updateFeedback(@ApiParam(value = "Update feedback object", required = true)@Valid @RequestBody Feedback feedback,@ApiParam(value = "Feedback Id to update feedback object", required = true)   @PathVariable("id") long feedbackId) {
		logger.trace("updateFeedback method in feedback controller accessed");
		return this.feedbackservice.updateFeedback(feedback, feedbackId);
	}
	
	@ApiOperation(value = "Get a list of Feedback by Trainer Id")
	@GetMapping("/getbytrainer/{id}")
	public List<Feedback> getTrainerFeedbacks(@ApiParam(value = "Trainer id from which feedback object will be retrieved", required = true)@PathVariable("id") String trainerId) {
    	logger.trace("getTrainerFeedbacks method in feedback controller accessed");
		return this.feedbackservice.viewFeedbackByTrainerId(trainerId);
	}
	
	@ApiOperation(value = "Get a list of Feedback by Program Id")
	@GetMapping("/getbyprogram/{id}")
	public List<Feedback> getProgramFeedbacks(@ApiParam(value = "Program id from which feedback object will be retrieved", required = true)@PathVariable("id") String programid) {
    	logger.trace("getProgramFeedbacks method in feedback controller accessed");
		return this.feedbackservice.viewFeedbackByProgramId(programid);
	}
	
}