package com.Event.Vendor;

import com.Event.Order.TypesOfOrder;
import com.Event.user.User;
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


