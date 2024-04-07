package com.eventManagement.orderservice.Repositories;

import com.eventManagement.orderservice.models.EventOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventOrderRepository extends JpaRepository<EventOrder, Long> {
    public EventOrder findByUserIdAndEventName(Integer userId, String eventName);
    public List<EventOrder> findByUserId(Integer userId);
    public EventOrder findByEventName(String eventName);
}
