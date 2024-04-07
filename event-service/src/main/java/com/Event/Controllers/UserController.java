package com.Event.Controllers;

import com.Event.EventOrder.EventOrder;
import com.Event.Order.Order;
import com.Event.Services.EventOrderService;
import com.Event.models.EventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private EventOrderService eventOrderService;

    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(au -> {
            System.out.println(au.getAuthority());
        });
        return ResponseEntity.ok("Hello Brother");
    }

    @GetMapping("/getEvents/{id}")
    public List<EventDetails> getEvents(@PathVariable Integer id) {
        return eventOrderService.getEvents(id);
    }
}
