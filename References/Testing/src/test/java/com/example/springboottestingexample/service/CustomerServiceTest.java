package com.example.springboottestingexample.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.exception.CustomerRegistrationException;
import com.example.springboottestingexample.repository.CustomerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
	
	private CustomerService customerService;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
    	customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void shouldSavedUserSuccessfully() throws CustomerRegistrationException {
    	Customer customer = new Customer(null, "siva@gmail.com","siva","Siva");
        given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.empty());
        given(customerRepository.save(customer)).willAnswer(invocation -> invocation.getArgument(0));

        Customer savedCustomer = customerService.createCustomer(customer);

        assertThat(savedCustomer).isNotNull();

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingEmail() {
    	Customer customer = new Customer(1L, "siva@gmail.com","siva","Siva");
        given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.of(customer));

        assertThrows(CustomerRegistrationException.class, () -> {
            customerService.createCustomer(customer);
        });

        verify(customerRepository, never()).save(any(Customer.class));
    }

}
