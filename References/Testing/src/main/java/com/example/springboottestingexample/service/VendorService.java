package com.example.springboottestingexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.entity.Vendor;
import com.example.springboottestingexample.exception.VendorRegistrationException;
import com.example.springboottestingexample.repository.CustomerRepository;
//import com.example.springboottestingexample.repository.CustomerRepository;
import com.example.springboottestingexample.repository.VendorRepository;

@Service
@Transactional
public class VendorService implements IVendorService{

	@Autowired
    private final VendorRepository vendorRepository;
	
	public VendorService(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}
	
	@Override
	public Optional<Vendor> login(String email, String password) {
		// TODO Auto-generated method stub
		return vendorRepository.login(email, password);
	}

	@Override
	public Vendor createVendor(Vendor vendor) throws VendorRegistrationException {
		// TODO Auto-generated method stub
		Optional<Vendor> vendorOptional = vendorRepository.findByEmail(vendor.getVenEmail());
		if(vendorOptional.isPresent()) {
			throw new VendorRegistrationException("Vendor with email "+ vendor.getVenEmail()+" already exists");
		}
		return vendorRepository.save(vendor);
	}

	@Override
	public Vendor updateVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorRepository.save(vendor);
	}

	@Override
	public List<Vendor> findAllVendors() {
		// TODO Auto-generated method stub
		return vendorRepository.findAll();
	}

	@Override
	public Optional<Vendor> findVendorById(Long id) {
		// TODO Auto-generated method stub
		return vendorRepository.findById(id);
	}

	@Override
	public void deleteVendorById(Long id) {
		// TODO Auto-generated method stub
		vendorRepository.deleteById(id);
	}

}
