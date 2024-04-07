package com.Event.Controllers;

import com.Event.EventOrder.EventOrder;
import com.Event.Order.CateringOrder;
import com.Event.Order.CreateOrderRequest;
import com.Event.Order.FlowersOrder;
import com.Event.Services.EventOrderService;
import com.Event.Services.OrderService;
import com.Event.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private EventOrderService eventOrderService;

    private ObjectMapper mapper = new ObjectMapper();
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody CreateOrderRequest event) {
        EventOrder eventOrder = new EventOrder();
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        eventOrder.setOrderId(new ArrayList<>());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
       if(!Optional.ofNullable(eventOrderService.getEventOrderByUseridAndEventName(user.getId(), event.getEventName())).isEmpty()){
           return new ResponseEntity<>("Duplicate Event Name", HttpStatus.INTERNAL_SERVER_ERROR);
       }
        eventOrder.setUserId(user.getId());
        eventOrder.setEventName(event.getEventName());
        event.getOrders().forEach(order -> {
            switch(order.getTypeOfOrder()){
                case FLOWERS -> {
                    eventOrder.getOrderId().add(orderService.saveFlowersOrder((FlowersOrder) order).getId());
                }
                case CATERING -> {
                    eventOrder.getOrderId().add(orderService.saveCateringOrder((CateringOrder) order).getId());
                }
            }

        });
        eventOrderService.saveEventOrder(eventOrder);
        return responseBuilder.build();
    }
    @GetMapping("/check")
    public String check() {
        return "all good";
    }
}
