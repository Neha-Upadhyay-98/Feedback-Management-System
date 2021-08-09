package com.cg.fms.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.fms.entity.Admin;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.repository.IAdminRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AdminServiceTest {
	
	private AdminService adminService;
	private IAdminRepository adminRepository;
	
	@BeforeEach
	void setUp() {
		adminRepository = mock(IAdminRepository.class);
		adminService = new AdminService(adminRepository);
	}

	@Test
	void shouldSaveAdminSuccessfully() throws UserNotFoundException {
		Admin admin = new Admin("123455","joysurjya","Joy@98","Joysurjya@98");
		given(adminRepository.findById(admin.getAdminId())).willReturn(Optional.empty());
		given(adminRepository.save(admin)).willAnswer(invocation -> invocation.getArgument(0));
		
		String savedAdmin = adminService.addAdmin(admin.getAdminName(),admin.getAdminUserName(),admin.getAdminPassword(), true);
		assertThat(savedAdmin).isNotNull();
		verify(adminRepository).save(any(Admin.class));
	}
	
	
	
}

