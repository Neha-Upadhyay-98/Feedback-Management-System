package com.cg.fms.controller;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is an controller class for Login
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@Api(value = "Feedback Management System",description = "Operations pertaining to Login Module of Feedback Management System")
@Validated
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
    @ApiOperation(value = "Login and access your account", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully logged in"),
        @ApiResponse(code = 401, message = "You are not authorized to login"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/login")
	public String login(@ApiParam(value = "Login credentials to login", required = true)@Valid @RequestBody Map<String, String> auth) {
		logger.trace("login method in login controller accessed");
    	try {
		return loginService.login(auth.get("username"),auth.get("password"), false);
	}
		catch(UserNotFoundException e) {
			logger.warn("Login failed");
			return e.getMessage();
		}
	}	
	
    @ApiOperation(value = "Logout your account", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully logged out"),
        @ApiResponse(code = 401, message = "You are not authorized to login"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/logout")
	public String logout(@ApiParam(value = "Logout credentials to logout", required = true)@Valid @RequestBody Map<String, String> auth) {
		logger.trace("logout method in logout controller accessed");
    	try {
		return loginService.logout(auth.get("username"),auth.get("password"));
	}
		catch(UserNotFoundException e) {
			logger.warn("Logout failed");
			return e.getMessage();
		}
	}	
    
    @ApiOperation(value = "Create an account by signing up")
	@PostMapping(value = "/signup", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String signup(@ApiParam(value = "Login credentials to sign up", required = true)@Valid @RequestBody Map<String, String> auth) {
    	logger.trace("signup method in login controller accessed");
    	try {
			return loginService.signup(auth.get("username"),auth.get("password"));
		}
		catch(RecordAlreadyExistsException e) {
			logger.warn("Signup failed");
			return e.getMessage();
		}
	}
	
    @ApiOperation(value = "Delete an account")
	@PostMapping("/delete/{uId}")
	public void deleteAccount(@ApiParam(value = "User Id to delete account object from database table")@PathVariable(value="uId") String userid) {
    	logger.trace("deleteAccount method in login controller accessed");
    	loginService.deleteAccount(userid);
	}
}