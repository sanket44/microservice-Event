package com.Event.Order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

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
