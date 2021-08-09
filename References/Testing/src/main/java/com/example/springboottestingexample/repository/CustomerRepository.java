package com.example.springboottestingexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboottestingexample.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("select u from Customer u where u.email=?1 and u.password=?2")
	Optional<Customer> login(String email, String password);

    Optional<Customer> findByEmail(String email);

}
