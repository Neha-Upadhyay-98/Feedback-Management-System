package com.example.springboottestingexample.service;

import java.util.List;
import java.util.Optional;

import com.example.springboottestingexample.entity.Vendor;
import com.example.springboottestingexample.exception.VendorRegistrationException;


public interface IVendorService {
	public Optional<Vendor> login(String email, String password);
	public Vendor createVendor(Vendor vendor) throws VendorRegistrationException;
	public Vendor updateVendor(Vendor vendor);
	public List<Vendor> findAllVendors();
	public Optional<Vendor> findVendorById(Long id);
	public void deleteVendorById(Long id);
}
