package com.eventManagement.vendorservice.models;

import com.eventManagement.vendorservice.Order.TypesOfOrder;
import com.eventManagement.vendorservice.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String companyName;
    @OneToOne
    @MapsId
    private User user;
    private List<TypesOfOrder> typesOfServices;


}
