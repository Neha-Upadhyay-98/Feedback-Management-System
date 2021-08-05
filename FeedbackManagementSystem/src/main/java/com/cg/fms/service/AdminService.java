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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Admin;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.IAdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private IAdminRepository adminrepository;
	
	public AdminService(IAdminRepository adminRepository) {
		this.adminrepository = adminRepository;
	}

	public String addAdmin(String adminName,String adminUserName, String adminPassword) throws RecordAlreadyExistsException {
		String adminId = Integer.toString(adminUserName.hashCode());
		Admin entry = new Admin(adminId, adminName, adminUserName, adminPassword);
		if(adminrepository.existsById(adminId)) {
			throw new RecordAlreadyExistsException ("Username " + adminUserName + " already exists!");
		}
		else {
			this.adminrepository.save(entry);
			return "Admin added";
		}	
	}

	public Admin updateAdmin(Admin admin, String adminId) {
		Admin existingadmin = this.adminrepository.findById(adminId).get();
		existingadmin.setAdminName(admin.getAdminName());
		existingadmin.setAdminUserName(admin.getAdminUserName());
		existingadmin.setAdminPassword(admin.getAdminPassword());
		return this.adminrepository.save(existingadmin);
	}

	public ResponseEntity<Admin> removeAdmin(String adminId) {
		Admin existingadmin = this.adminrepository.getById(adminId);
		this.adminrepository.delete(existingadmin);
		return ResponseEntity.ok().build();
	}

	public Admin viewAdminById(String adminId) {
		return this.adminrepository.findById(adminId)
				.orElseThrow(() -> new UserNotFoundException("Admin not found with id " + adminId));
	}
	
	public List<Admin> viewAllAdmins() {
		return this.adminrepository.findAll();
	}

}
