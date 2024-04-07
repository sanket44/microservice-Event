package com.eventManagement.orderservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "vendor-service",url ="localhost:8080" )
@FeignClient(name = "vendor-service")
public interface VendorServiceProxy {
    @GetMapping("/vendor/getVendorOrders/{id}")
    public String getVendorOrders(@PathVariable Integer id);
}
