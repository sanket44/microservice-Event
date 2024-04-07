//package com.eventManagement.orderservice.controllers;
//
//import com.eventManagement.orderservice.Order.Order;
//import com.eventManagement.orderservice.Repositories.OrderRepository;
//import com.eventManagement.orderservice.config.VendorServiceProxy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//    @Autowired
//    private Environment environment;
//    @Autowired
//    VendorServiceProxy vendorServiceProxy;
//    @Autowired
//    OrderRepository orderRepository;
//
//    @GetMapping("/getInstance")
//    public String getInstance() {
////        return environment.getProperty("local.server.port");
//        return vendorServiceProxy.getVendorOrders(78);
//    }
//    @PreAuthorize("hasAuthority('vendor')")
//    @GetMapping("/getOrders")
//    public List<Order> getOrders() {
////        return environment.getProperty("local.server.port");
////        orderRepository.findAllByVendorId(3).forEach(order -> System.out.println(order.));
//        return orderRepository.findAllByVendorId(3);
//    }
//}
package com.eventManagement.orderservice.controllers;

import com.eventManagement.orderservice.Order.CateringOrder;
import com.eventManagement.orderservice.Order.CreateOrderRequest;
import com.eventManagement.orderservice.Order.FlowersOrder;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
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
}
