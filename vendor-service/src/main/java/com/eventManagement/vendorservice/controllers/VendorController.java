package com.eventManagement.vendorservice.controllers;

import com.eventManagement.vendorservice.configurations.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private Configuration configuration;
    @Autowired
    private Environment environment;
    @GetMapping("/getVendorOrders/{id}")
    public String getVendorOrders(@PathVariable Integer id) {
        return "In process  "+environment.getProperty("local.server.port")+"  ---"+(id*2);
    }

}

