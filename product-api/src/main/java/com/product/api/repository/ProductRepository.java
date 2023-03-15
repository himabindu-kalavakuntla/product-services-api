package com.product.api.repository;

import com.product.api.entity.ProductTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductTest, Integer> {
}
