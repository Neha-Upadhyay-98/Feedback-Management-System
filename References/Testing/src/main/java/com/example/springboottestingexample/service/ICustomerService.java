package com.example.springboottestingexample.service;

import java.util.List;
import java.util.Optional;

import com.example.springboottestingexample.entity.Customer;


public interface ICustomerService {
	
	public Optional<Customer> login(String email, String password);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public List<Customer> findAllCustomers();
	public Optional<Customer> findCustomerById(Long id);
	public void deleteCustomerById(Long id);

}
