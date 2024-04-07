package com.eventManagement.orderservice.models;

import com.eventManagement.orderservice.Order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetails {
    private String eventName;
    private List<Order> orders;
}
