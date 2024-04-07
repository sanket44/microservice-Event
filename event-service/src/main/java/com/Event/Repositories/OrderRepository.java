package com.Event.Repositories;

import com.Event.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
 public List<Order> findAllByVendorId(Integer vendorId);
}
