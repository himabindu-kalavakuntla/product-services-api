package com.product.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse {
    private int id;
    private String firstname;
    private String lastname;
    public CustomerResponse(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }
}
