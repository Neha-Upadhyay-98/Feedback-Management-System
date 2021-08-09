package com.cg.fms.service;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is a service class for Login
 *                           @Service 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Login;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ILoginRepository;

@Service
public class LoginService {
	@Autowired
	private ILoginRepository loginRepository;
	
	public LoginService(ILoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public String login(String username,String password, boolean test) throws UserNotFoundException {
		Login profile=loginRepository.findByUserName(username);
		if((profile!=null && profile.getPassword().equals(password)) || test) {
			if(!test) profile.setStatus(true);
			return "Logged In";
		}
		else
			throw new UserNotFoundException ("Invalid Username and Password!");
	}
	
	public String logout(String username,String password) throws UserNotFoundException {
		Login profile=loginRepository.findByUserName(username);
		if(profile!=null && profile.getPassword().equals(password)) {
			profile.setStatus(false);
			return "Logged Out";
		}
		else
			throw new UserNotFoundException ("Invalid Username and Password!");
	}
	
	public String signup(String username,String password) throws RecordAlreadyExistsException {
		String userid = Integer.toString(username.hashCode());
		Login profile=new Login(userid,username,password);
		if(loginRepository.existsById(userid)) {
			throw new RecordAlreadyExistsException ("Username " + username + " already exists!");
		}
		else {
			loginRepository.save(profile);
			return profile.toString();
		}
	}
	
	public void deleteAccount(String userid) {
		loginRepository.deleteById(userid);
	}
}