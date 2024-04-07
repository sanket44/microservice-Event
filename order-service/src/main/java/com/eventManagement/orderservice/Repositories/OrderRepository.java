package com.eventManagement.orderservice.Repositories;

import com.eventManagement.orderservice.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
 public List<Order> findAllByVendorId(Integer vendorId);
}
