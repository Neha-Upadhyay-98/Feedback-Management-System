package com.example.springboottestingexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboottestingexample.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	@Query("select u from Vendor u where u.email=?1 and u.password=?2")
	Optional<Vendor> login(String email, String password);

    Optional<Vendor> findByEmail(String email);
}
