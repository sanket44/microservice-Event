package com.eventManagement.orderservice.services;

import com.eventManagement.orderservice.Repositories.EventOrderRepository;
import com.eventManagement.orderservice.Repositories.OrderRepository;
import com.eventManagement.orderservice.models.EventDetails;
import com.eventManagement.orderservice.models.EventOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventOrderService {
    @Autowired
    private EventOrderRepository eventOrderRepository;
    @Autowired
    private OrderRepository orderRepository;


    public void saveEventOrder(EventOrder eventOrder) {
        eventOrderRepository.save(eventOrder);
    }

    public EventOrder getEventOrderByUseridAndEventName(Integer userId, String eventName) {
        return eventOrderRepository.findByUserIdAndEventName(userId, eventName);
    }

    public List<EventDetails> getEvents(Integer id) {
        List<EventDetails> eventDetailsList = new ArrayList<>();
        List<EventOrder> list = eventOrderRepository.findByUserId(id);
        list.forEach(eventOrder -> {
            EventDetails eventDetails = EventDetails.builder()
                    .eventName(eventOrder.getEventName())
                    .orders(orderRepository.findAllById(eventOrder.getOrderId())).build();
            eventDetailsList.add(eventDetails);
        });
        return eventDetailsList;
    }

}