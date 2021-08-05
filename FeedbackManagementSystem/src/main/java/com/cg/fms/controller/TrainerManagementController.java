package com.cg.fms.controller;

/*****************************************************************
 *          @author          Sneha Chakraborty
 *          Description      It is an controller class for Trainer
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 ******************************************************************/

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Trainer;
import com.cg.fms.service.TrainerManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fms/trainer")
@Api(value = "Feedback Management System",description = "Operations pertaining to Trainer of Feedback Management System")
@Validated
public class TrainerManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public TrainerManagementService trainermanagementservice;
	
	@ApiOperation(value = "Add a Trainer")
	@PostMapping("/add")
	public Trainer addTrainer(@ApiParam(value = "trainer object stored in database table", required = true)@Valid @RequestBody Trainer trainer) {
		logger.trace("addTrainer method in TrainerManagement controller accessed");
		return this.trainermanagementservice.addTrainer(trainer);
	}
	
	@ApiOperation(value = "Update a Trainer")
	@PutMapping("/update/{id}")
	public Trainer updateTrainer(@ApiParam(value = "Update trainer object", required = true)@Valid @RequestBody Trainer trainer,@ApiParam(value = "Trainer Id to update trainer object", required = true) @PathVariable("id") String trainerId) {
		logger.trace("updateTrainer method in TrainerManagement controller accessed");
		return this.trainermanagementservice.updateTrainer(trainer, trainerId);
	}
	
	@ApiOperation(value = "Delete a Trainer")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity <Trainer> removeTrainer(@ApiParam(value = "Trainer Id to delete trainer object from database table") @PathVariable("id") String trainerId) {
		logger.trace("removeTrainer method in TrainerManagement controller accessed");
		return this.trainermanagementservice.removeTrainer(trainerId);
	}
	
    @ApiOperation(value = "View a list of trainers", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/viewall")
	public List<Trainer> viewAllTrainer() {
    	logger.trace("viewAllTrainer method in TrainerManagement controller accessed");
		return this.trainermanagementservice.viewAllTrainer();
	}
	
    @ApiOperation(value = "Get a trainer by Id")
	@GetMapping("/viewbyid/{id}")
	public Trainer viewTrainerById(@ApiParam(value = "Trainer id from which trainer object will be retrieved", required = true) @PathVariable("id") @Positive String trainerId) {
    	logger.trace("viewTrainer method in TrainerManagement controller accessed");
		return this.trainermanagementservice.viewTrainerById(trainerId);
	}
	
    @ApiOperation(value = "Get a trainer by skill")
	@GetMapping("/viewbyskill/{skill}")
	public List<Trainer> viewAllTrainerBySkill(@ApiParam(value = "Skill from which trainer object will be retrieved", required = true)@PathVariable("skill") @Positive String skill){
    	logger.trace("viewAllTrainerBySkill method in TrainerManagement controller accessed");
		return this.trainermanagementservice.viewAllTrainerBySkill(skill);
	}

}