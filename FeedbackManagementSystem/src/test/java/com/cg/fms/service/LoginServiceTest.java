package com.cg.fms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;

import com.cg.fms.entity.Login;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ILoginRepository;

class LoginServiceTest {

	private LoginService loginService;
	
	private ILoginRepository loginRepository;
	
    @BeforeEach
    void setUp() {
    	loginRepository = mock(ILoginRepository.class);
    	loginService = new LoginService(loginRepository);
    }
	
    @Test
    void shouldSavedUserSuccessfully() throws RecordAlreadyExistsException {
    	Login user = new Login("001","Name@123","Password@1234");
        given(loginRepository.findById(user.getUserid())).willReturn(Optional.empty());
        given(loginRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        String savedUser = loginService.login(user.getUserName(),user.getPassword());

        assertThat(savedUser).isNotNull();

        verify(loginRepository).save(any(Login.class));
    }

}
