package com.product.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
@NoArgsConstructor
@Table(name = "test_data")
public class ProductTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "customer_id")
    private int customerId;
    private Blob productdata;

    public ProductTest(int customerId) {
        customerId = this.customerId;
    }
}
