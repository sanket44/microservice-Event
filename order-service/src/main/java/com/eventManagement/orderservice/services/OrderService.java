package com.eventManagement.orderservice.services;

import com.eventManagement.orderservice.Order.CateringOrder;
import com.eventManagement.orderservice.Order.FlowersOrder;
import com.eventManagement.orderservice.Order.Order;
import com.eventManagement.orderservice.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public FlowersOrder saveFlowersOrder(FlowersOrder flowersOrder) {
        flowersOrder.getFlowerDetails().forEach(detail -> detail.setFlowersOrder(flowersOrder));
        return orderRepository.save(flowersOrder);
    }

    public CateringOrder saveCateringOrder(CateringOrder cateringOrder) {
        return orderRepository.save(cateringOrder);
    }

    public List<Order> getOrdersForVendors(Integer vendorId){
      return  orderRepository.findAllByVendorId(vendorId);
    }
}
