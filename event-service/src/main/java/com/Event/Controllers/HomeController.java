package com.Event.Controllers;

import com.Event.EventOrder.EventOrder;
import com.Event.Repositories.VendorRepository;
import com.Event.Services.EventOrderService;
import com.Event.Services.VendorService;
import com.Event.Vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @Autowired
    private VendorService vendorService;
    @Autowired
    private EventOrderService eventOrderService;
    @GetMapping("/")
    public List<Vendor> getVendors() {
        return vendorService.getAllvendors();
    }
    @GetMapping("/{id}")
    public Optional<Vendor> getVendor(@PathVariable Integer id) {
        return vendorService.getvendor(id);
    }
    @GetMapping("/eventName")
    public Optional<EventOrder> getEventOrder() {
        return Optional.ofNullable(eventOrderService.getEventOrderByUseridAndEventName(1, "reception"));
    }
}
