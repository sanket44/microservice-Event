package com.eventManagement.vendorservice.configurations;

import com.eventManagement.vendorservice.Order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceProxy {
    @GetMapping("/order/getVendorOrders/{id}")
    public List<Order> getVendorOrders(@PathVariable Integer id);
}
