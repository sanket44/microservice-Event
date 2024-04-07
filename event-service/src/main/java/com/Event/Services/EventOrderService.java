package com.Event.Services;

import com.Event.EventOrder.EventOrder;
import com.Event.Repositories.EventOrderRepository;
import com.Event.Repositories.OrderRepository;
import com.Event.models.EventDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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