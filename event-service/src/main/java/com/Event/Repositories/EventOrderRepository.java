package com.Event.Repositories;

import com.Event.EventOrder.EventOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventOrderRepository extends JpaRepository<EventOrder, Long> {
    public EventOrder findByUserIdAndEventName(Integer userId,String eventName);
    public List<EventOrder> findByUserId(Integer userId);
    public EventOrder findByEventName(String eventName);
}
