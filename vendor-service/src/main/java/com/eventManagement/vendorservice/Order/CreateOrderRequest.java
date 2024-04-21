package com.eventManagement.vendorservice.Order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = CreateOrderRequestDeserializer.class)
public class CreateOrderRequest {

    private String eventName;
    private List< ? extends  Order> orders ;

}
