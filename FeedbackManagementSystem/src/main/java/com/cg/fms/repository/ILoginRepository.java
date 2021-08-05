package com.cg.fms.repository;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is a repository interface for Login
 *                           @Repository 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login,String>{
	
	public Login findByUserName(String username);
}