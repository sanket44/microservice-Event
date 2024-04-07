package com.Event.Order;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@DiscriminatorValue("FLOWERS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class FlowersOrder extends Order {
    @OneToMany(mappedBy = "flowersOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlowerDetail> flowerDetails = new ArrayList<>();
}
