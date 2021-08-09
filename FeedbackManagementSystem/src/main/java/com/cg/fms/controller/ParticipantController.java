package com.cg.fms.controller;

/*********************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is an controller class for Participant
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 *********************************************************************/

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Participant;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.service.ParticipantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fms/participant")
@Api(value = "Feedback Management System",description = "Operations pertaining to Paticipant of Feedback Management System")
@Validated
public class ParticipantController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public ParticipantService participantservice;
	
	@ApiOperation(value = "Participant login")
	@PostMapping(value = "/login")
	public String participantLogin(@ApiParam(value = "Participant Login credentials to login", required = true)@Valid @RequestBody Map<String, String> part) {
		logger.trace("participantLogin method in participant controller accessed");
    	try {
    		return participantservice.participantLogin(part.get("participantUserName"),part.get("participantPassword"));
    	}
		catch(UserNotFoundException e) {
			logger.warn("Participant Login failed");
			return e.getMessage();
		}
	}	
	
	@ApiOperation(value = "Participant logout")
	@PostMapping(value = "/logout")
	public String participantLogout(@ApiParam(value = "Participant Logout credentials to logout", required = true)@Valid @RequestBody Map<String, String> part) {
		logger.trace("participantLogout method in participant controller accessed");
    	try {
    		return participantservice.participantLogout(part.get("participantUserName"),part.get("participantPassword"));
    	}
		catch(UserNotFoundException e) {
			logger.warn("Participant logout failed");
			return e.getMessage();
		}
	}	
	
	@ApiOperation(value = "Add a Participant")
	@PostMapping(value = "/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String addParticipant(@ApiParam(value = "Participant info to add", required = true)@Valid @RequestBody Map<String, String> part) {
    	logger.trace("addParticipant method in partcipant controller accessed");
    	try {
			return participantservice.addParticipant(part.get("participantName"),part.get("participantUserName"), part.get("participantPassword"), false);
		}
		catch(RecordAlreadyExistsException e) {
			logger.warn("Participant already exists");
			return e.getMessage();
		}
	}
	
	@ApiOperation(value = "Update a Participant")
	@PutMapping("/update/{id}")
	public Participant updateParticipant(@ApiParam(value = "Update participant object", required = true)@Valid @RequestBody Participant participant,@ApiParam(value = "Participant Id to update participant object", required = true)   @PathVariable("id") String participantId) {
		logger.trace("updateParticipant method in participant controller accessed");
		return this.participantservice.updateParticipant(participant, participantId);
	}
	
	@ApiOperation(value = "Delete a Participant")
	@DeleteMapping("/delete/{id}")
	public String removeParticipant(@ApiParam(value = "Participant Id to delete participant object from database table")@PathVariable("id") String participantId) {
		logger.trace("removeParticipant method in participant controller accessed");
		return this.participantservice.removeParticipant(participantId);
	}
	
    @ApiOperation(value = "View a list of participants", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/viewall")
	public List<Participant> viewAllParticipants() {
    	logger.trace("viewAllParticipant method in participant controller accessed");
		return this.participantservice.viewAllParticipants();
	}
	
    @ApiOperation(value = "Get a participant by Id")
	@GetMapping("/viewbyid/{id}")
	public Participant viewParticipantById(@ApiParam(value = "Participant id from which participant object will be retrieved", required = true) @PathVariable("id") String participantId) {
    	logger.trace("viewParticipant method in participant controller accessed");
		return this.participantservice.viewParticipantById(participantId);
	}

}
