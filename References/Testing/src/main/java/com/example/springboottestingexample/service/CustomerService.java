package com.example.springboottestingexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.exception.CustomerRegistrationException;
import com.example.springboottestingexample.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService 
{
	 @Autowired
    private final CustomerRepository customerRepository;

   
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Optional<Customer> login(String email, String password)
	{
		return customerRepository.login(email, password);
	}
	
	public Customer createCustomer(Customer customer) throws CustomerRegistrationException
	{
		Optional<Customer> CustomerOptional = customerRepository.findByEmail(customer.getEmail());
		if(CustomerOptional.isPresent()){
			throw new CustomerRegistrationException("Customer with email "+ customer.getEmail()+" already exists");
		}
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}

	public List<Customer> findAllCustomers() {
    	return customerRepository.findAll();
	}

	public Optional<Customer> findCustomerById(Long id) {
    	return customerRepository.findById(id);
	}

	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}
	
}
