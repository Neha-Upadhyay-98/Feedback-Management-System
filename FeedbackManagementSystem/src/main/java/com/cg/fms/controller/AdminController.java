package com.cg.fms.controller;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is an controller class for Admin
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Admin;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fms/admin")
@Api(value = "Feedback Management System", description = "Operations pertaining to Admin of Feedback Management System")
@Validated
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public AdminService adminservice;
	
	@ApiOperation(value = "Admin login")
	@PostMapping(value = "/login")
	public String adminLogin(@ApiParam(value = "Admin Login credentials to login", required = true)@Valid @RequestBody Map<String, String> admn) {
		logger.trace("adminLogin method in admin controller accessed");
    	try {
    		return adminservice.adminLogin(admn.get("adminUserName"),admn.get("adminPassword"));
    	}
		catch(UserNotFoundException e) {
			logger.warn("Admin login failed");
			return e.getMessage();
		}
	}	
	
	@ApiOperation(value = "Admin logout")
	@PostMapping(value = "/logout")
	public String adminLogout(@ApiParam(value = "Admin Logout credentials to logout", required = true)@Valid @RequestBody Map<String, String> admn) {
		logger.trace("adminLogout method in admin controller accessed");
    	try {
    		return adminservice.adminLogout(admn.get("adminUserName"),admn.get("adminPassword"));
    	}
		catch(UserNotFoundException e) {
			logger.warn("Admin logout failed");
			return e.getMessage();
		}
	}	
	
	@ApiOperation(value = "Add an admin")
	@PostMapping(value = "/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String addAdmin(@ApiParam(value = "Admin info to add", required = true)@Valid @RequestBody Map<String, String> adm) {
    	logger.trace("signup method in admin controller accessed");
    	try {
			return adminservice.addAdmin(adm.get("adminName"),adm.get("adminUserName"), adm.get("adminPassword"), false);
		}
		catch(RecordAlreadyExistsException e) {
			logger.warn("Admin already exists");
			return e.getMessage();
		}
	}
	
	@ApiOperation(value = "Update an Admin")
	@PutMapping("/update/{id}")
	public String updateAdmin(@ApiParam(value = "Update admin object", required = true)@Valid @RequestBody Admin admin,  @ApiParam(value = "Admin Id to update admin object", required = true) @PathVariable("id") String adminId) {
		logger.trace("updateAdmin method in admin controller accessed");
		return this.adminservice.updateAdmin(admin, adminId);
	}
	
	@ApiOperation(value = "Delete an Admin")
	@DeleteMapping("/delete/{id}")
	public String removeAdmin(@ApiParam(value = "Admin Id from which admin object will delete from database table", required = true) @PathVariable("id") String adminId) {
		logger.trace("removeAdmin method in admin controller accessed");
		return this.adminservice.removeAdmin(adminId);
	}
	
	@ApiOperation(value = "Get an admin by Id")
	@GetMapping("/viewbyid/{id}")
	public Admin viewAdminById(@ApiParam(value = "Admin id from which admin object will retrieve", required = true) @PathVariable("id") String adminId) {
    	logger.trace("viewAdmin method in admin controller accessed");
    	return this.adminservice.viewAdminById(adminId);
	}
	
    @ApiOperation(value = "View a list of admins", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/viewall")
	public List<Admin> viewAllAdmins() {
    	logger.trace("viewAllAdmin method in admin controller accessed");
		return this.adminservice.viewAllAdmins();
	}


}
