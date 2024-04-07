package com.Event.Order;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestDeserializer extends StdDeserializer<CreateOrderRequest> {

    private ObjectMapper mapper = new ObjectMapper();

    public CreateOrderRequestDeserializer() {
        this(null);
    }

    public CreateOrderRequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CreateOrderRequest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String eventName = node.get("eventName").asText();

        List<? extends Order> orders = deserializeOrders(node);

        return new CreateOrderRequest(eventName, orders);
    }

    private List<? extends Order> deserializeOrders(JsonNode node) throws IOException {
        JsonNode ordersNode = node.get("orders");
        List<Order> orders = new ArrayList<>();

        // Deserialize each order in the list
        for (JsonNode orderNode : ordersNode) {
            System.err.println(orderNode);
            switch (orderNode.get("typeOfOrder").asText()) {
                case "FLOWERS" -> {
                    FlowersOrder flowersOrder = mapper.convertValue(orderNode, FlowersOrder.class);
                    orders.add(flowersOrder);
                }
                case "CATERING" -> {
                    CateringOrder cateringOrder = mapper.convertValue(orderNode, CateringOrder.class);
                    orders.add(cateringOrder);
                }
            }
        }
        return orders;
    }
}
