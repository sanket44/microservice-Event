package com.Event.auth;

import com.Event.Order.TypesOfOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;

    private String role;
    private String companyName;

    private List<TypesOfOrder> typesOfServices;
}
