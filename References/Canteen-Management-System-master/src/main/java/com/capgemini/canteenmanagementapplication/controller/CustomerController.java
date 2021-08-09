package com.capgemini.canteenmanagementapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.canteenmanagementapplication.exception.ResourceNotFoundException;
import com.capgemini.canteenmanagementapplication.model.Customer;
import com.capgemini.canteenmanagementapplication.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//get all customers
	@GetMapping("/customers")
	public List<Customer> getCustomerList()
	{
		return customerRepository.findAll();
	}
	
	//add new customer
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer customer)
	{
		return customerRepository.save(customer);
	}

	//get a customer by id
	@GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
        throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);
    }
	
	//get a customer by username and email
	@GetMapping("/customers/{username}/{email}")
	public ResponseEntity<Customer> getCustomerByUsernameAndEmail(@PathVariable(value = "username") String username,
			@PathVariable(value ="email") String email) throws ResourceNotFoundException {
	        Customer customer = customerRepository.findByUsernameAndEmail(username, email)
	          .orElseThrow(() -> new ResourceNotFoundException("Customer not found "));
	        return ResponseEntity.ok().body(customer);
	    }
	
	//update a customer
	@PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
         @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setEmail(customerDetails.getEmail());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        customer.setBalance(customerDetails.getBalance());
        customer.setUsername(customerDetails.getUsername());
        customer.setPassword(customerDetails.getPassword());
        customer.setNumberOfOrders(customerDetails.getNumberOfOrders());
        
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
	
}
