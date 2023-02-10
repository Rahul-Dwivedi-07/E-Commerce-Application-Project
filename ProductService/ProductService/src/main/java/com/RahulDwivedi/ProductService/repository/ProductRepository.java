package com.RahulDwivedi.ProductService.repository;

import com.RahulDwivedi.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//JpaRepository is for the product entity and long is the id type
public interface ProductRepository extends JpaRepository<Product,Long> {
}
