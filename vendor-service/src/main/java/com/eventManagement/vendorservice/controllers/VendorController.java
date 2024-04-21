package com.eventManagement.vendorservice.controllers;

import com.eventManagement.vendorservice.Order.Order;
import com.eventManagement.vendorservice.Repositories.VendorRepository;
import com.eventManagement.vendorservice.configurations.Configuration;
import com.eventManagement.vendorservice.configurations.OrderServiceProxy;
import com.eventManagement.vendorservice.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private OrderServiceProxy orderServiceProxy;
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
        return orderServiceProxy.getVendorOrders(id);
    }
}

