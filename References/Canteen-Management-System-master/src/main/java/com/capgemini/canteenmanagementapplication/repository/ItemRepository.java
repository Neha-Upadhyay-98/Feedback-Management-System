package com.capgemini.canteenmanagementapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.canteenmanagementapplication.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	List<Item> findByVendor_VendorId(Long vendorId);
	Optional<Item> findByItemIdAndVendor_VendorId(Long itemId, Long vendorId);

}
