package com.example.springboottestingexample.controller;


import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
public class CustomerControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private CustomerService customerService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Customer> userList;

	    @BeforeEach
	    void setUp() {
	        this.userList = new ArrayList<>();
	        this.userList.add(new Customer(1L, "user1@gmail.com", "pwd1","User1"));
	        this.userList.add(new Customer(2L, "user2@gmail.com", "pwd2","User2"));
	        this.userList.add(new Customer(3L, "user3@gmail.com", "pwd3","User3"));

	        //objectMapper.registerModule(new ProblemModule());
	        //objectMapper.registerModule(new ConstraintViolationProblemModule());
	    }

	    @Test
	    void shouldFetchAllUsers() throws Exception {
	        given(customerService.findAllCustomers()).willReturn(this.userList);

	        this.mockMvc.perform(get("/api/customers"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(userList.size())));
	    }

	    @Test
	    void shouldFindUserById() throws Exception {
	        Long userId = 1L;
	        Customer user = new Customer(userId, "newuser1@gmail.com", "pwd", "Name");
	        given(customerService.findCustomerById(userId)).willReturn(Optional.of(user));

	        this.mockMvc.perform(get("/api/customers/{id}", userId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(user.getEmail())))
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.name", is(user.getName())))
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingUser() throws Exception {
	        Long userId = 1L;
	        given(customerService.findCustomerById(userId)).willReturn(Optional.empty());

	        this.mockMvc.perform(get("/api/customers/{id}", userId))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldCreateNewUser() throws Exception {
	        given(customerService.createCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Customer customer = new Customer(null, "newuser1@gmail.com", "pwd", "Name");
	        this.mockMvc.perform(post("/api/customers")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(customer)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.email", is(customer.getEmail())))
	                .andExpect(jsonPath("$.password", is(customer.getPassword())))
	                .andExpect(jsonPath("$.name", is(customer.getName())))
	                ;

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutEmail() throws Exception {
	        Customer user = new Customer(null, null, "pwd", "Name");

	        this.mockMvc.perform(post("/api/customers")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isBadRequest())
	                .andExpect(header().string("Content-Type", is("application/problem+json")))
	                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
	                .andExpect(jsonPath("$.title", is("Constraint Violation")))
	                .andExpect(jsonPath("$.status", is(400)))
	                .andExpect(jsonPath("$.violations", hasSize(1)))
	                .andExpect(jsonPath("$.violations[0].field", is("email")))
	                .andExpect(jsonPath("$.violations[0].message", is("Email should not be empty")))
	                .andReturn()
	        ;
	    }

	    @Test
	    void shouldUpdateUser() throws Exception {
	        Long userId = 1L;
	        Customer user = new Customer(userId, "user1@gmail.com", "pwd", "Name");
	        given(customerService.findCustomerById(userId)).willReturn(Optional.of(user));
	        given(customerService.updateCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/api/customers/{id}", user.getId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(user.getEmail())))
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.name", is(user.getName())));

	    }

	    @Test
	    void shouldReturn404WhenUpdatingNonExistingUser() throws Exception {
	        Long userId = 1L;
	        given(customerService.findCustomerById(userId)).willReturn(Optional.empty());
	        Customer customer = new Customer(userId, "user1@gmail.com", "pwd", "Name");

	        this.mockMvc.perform(put("/api/customers/{id}", userId)
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(customer)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldDeleteUser() throws Exception {
	        Long userId = 1L;
	        Customer customer = new Customer(userId, "user1@gmail.com", "pwd", "Name");
	        given(customerService.findCustomerById(userId)).willReturn(Optional.of(customer));
	        doNothing().when(customerService).deleteCustomerById(customer.getId());

	        this.mockMvc.perform(delete("/api/customers/{id}", customer.getId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(customer.getEmail())))
	                .andExpect(jsonPath("$.password", is(customer.getPassword())))
	                .andExpect(jsonPath("$.name", is(customer.getName())));

	    }

	    @Test
	    void shouldReturn404WhenDeletingNonExistingUser() throws Exception {
	        Long userId = 1L;
	        given(customerService.findCustomerById(userId)).willReturn(Optional.empty());

	        this.mockMvc.perform(delete("/api/customers/{id}", userId))
	                .andExpect(status().isNotFound());

	    }

}
