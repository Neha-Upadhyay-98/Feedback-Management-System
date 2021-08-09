package com.cg.fms.service;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is a service class for Admin
 *                           @Service 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Admin;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.IAdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private IAdminRepository adminrepository;
	
	@Autowired
	private LoginService loginservice;
	
	int flag = 0;
	
	public AdminService(IAdminRepository adminRepository) {
		this.adminrepository = adminRepository;
	}
	
	public String adminLogin(String adminUserName, String adminPassword) {
		if(this.loginservice.login(adminUserName, adminPassword, false)=="Logged In") {
			flag = 1;
			return adminUserName + " successfully logged in!";
		}
		else {
			return "Login attempt failed";
		}
	}
	
	public String adminLogout(String adminUserName, String adminPassword) {
		if(this.loginservice.logout(adminUserName, adminPassword)=="Logged Out") {
			flag = 0;
			return adminUserName + " successfully logged out!";
		}
		else {
			return "Logout attempt failed";
		}
	}

	public String addAdmin(String adminName,String adminUserName, String adminPassword, boolean test) throws RecordAlreadyExistsException {
		String adminId = Integer.toString(adminUserName.hashCode());
		Admin entry = new Admin(adminId, adminName, adminUserName, adminPassword);
		if(adminrepository.existsById(adminId)) {
			throw new RecordAlreadyExistsException ("Username " + adminUserName + " already exists!");
		}
		else {
			try {
				if(!test) this.loginservice.signup(adminUserName, adminPassword);
				this.adminrepository.save(entry);
				return "Admin added";
			}
			catch(RecordAlreadyExistsException e) {
				return e.getMessage();
			}
		}	
	}

	public String updateAdmin(Admin admin, String adminId) {
		if(this.flag==1) {
			Admin existingadmin = this.adminrepository.findById(adminId).get();
			existingadmin.setAdminName(admin.getAdminName());
			existingadmin.setAdminPassword(admin.getAdminPassword());
			this.adminrepository.save(existingadmin);
			return "Admin with id " + adminId + "updated";
		}
		else {
			return "Admin login failed";
		}
	}

	public String removeAdmin(String adminId) {
		if(this.flag==1) {
			Admin existingadmin = this.adminrepository.getById(adminId);
			this.loginservice.deleteAccount(adminId);
			this.adminrepository.delete(existingadmin);
			return "Admin with id " + adminId + "deleted";
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public Admin viewAdminById(String adminId) {
		if(this.flag==1) {
			return this.adminrepository.findById(adminId)
				.orElseThrow(() -> new UserNotFoundException("Admin not found with id " + adminId));
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}
	
	public List<Admin> viewAllAdmins() {
		if(this.flag==1) {
			return this.adminrepository.findAll();
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
		
	}

}
