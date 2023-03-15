package com.product.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private int customerId;
    private int id;
    private String productData;
}
