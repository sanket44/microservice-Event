package com.Event.Services;

import com.Event.Order.CateringOrder;
import com.Event.Order.FlowersOrder;
import com.Event.Order.Order;
import com.Event.Repositories.OrderRepository;
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
