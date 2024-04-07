package com.Event.Controllers;

import com.Event.EventOrder.EventOrder;
import com.Event.Order.Order;
import com.Event.Repositories.OrderRepository;
import com.Event.Repositories.VendorRepository;
import com.Event.Services.EventOrderService;
import com.Event.Services.OrderService;
import com.Event.Vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EventOrderService eventOrderService;

    @GetMapping("/")
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }
    @GetMapping("/:id")
    public Optional<Vendor> getVendor(@RequestParam Integer id) {
        return vendorRepository.findById(id);
    }
    @GetMapping("/getVendorOrders/{id}")
    public List<Order> getVendorOrders(@PathVariable Integer id) {
        return orderService.getOrdersForVendors(id);
    }
}
