package com.example.springboottestingexample.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.exception.CustomerRegistrationException;
import com.example.springboottestingexample.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {
	
	@Autowired
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.findAllCustomers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return customerService.findCustomerById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody @Validated Customer Customer) throws CustomerRegistrationException {
		return customerService.createCustomer(Customer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer Customer) {
		return customerService.findCustomerById(id)
				.map(CustomerObj -> {
					Customer.setId(id);
					return ResponseEntity.ok(customerService.updateCustomer(Customer));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
		return customerService.findCustomerById(id)
				.map(Customer -> {
					customerService.deleteCustomerById(id);
					return ResponseEntity.ok(Customer);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}