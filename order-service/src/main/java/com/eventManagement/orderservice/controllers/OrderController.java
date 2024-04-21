package com.eventManagement.orderservice.controllers;

import com.eventManagement.orderservice.Order.CateringOrder;
import com.eventManagement.orderservice.Order.CreateOrderRequest;
import com.eventManagement.orderservice.Order.FlowersOrder;
import com.eventManagement.orderservice.Order.Order;
import com.eventManagement.orderservice.Repositories.UserRepository;
import com.eventManagement.orderservice.models.EventOrder;
import com.eventManagement.orderservice.services.EventOrderService;
import com.eventManagement.orderservice.services.OrderService;
import com.eventManagement.orderservice.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private EventOrderService eventOrderService;
    @Autowired
    private UserRepository userRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody CreateOrderRequest event) throws IllegalAccessException {
        EventOrder eventOrder = new EventOrder();
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        eventOrder.setOrderId(new ArrayList<>());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject();
        Optional<User> user = userRepository.findByEmail(email);
        if (!Optional.ofNullable(user).isPresent()) {
            return new ResponseEntity<>("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        eventOrder.setUserId(user.get().getId());
        if (Optional.ofNullable(eventOrderService.getEventOrderByUseridAndEventName(eventOrder.getUserId(), event.getEventName())).isPresent()) {
            return new ResponseEntity<>("Duplicate Event Name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        eventOrder.setEventName(event.getEventName());
        event.getOrders().forEach(order -> {
            switch (order.getTypeOfOrder()) {
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

    @GetMapping("/getVendorOrders/{id}")
    public List<Order> getVendorOrders(@PathVariable Integer id) {
        return orderService.getOrdersForVendors(id);
    }
}
