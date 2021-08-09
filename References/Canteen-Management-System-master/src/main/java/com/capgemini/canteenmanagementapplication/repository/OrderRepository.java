package com.capgemini.canteenmanagementapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.canteenmanagementapplication.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}
