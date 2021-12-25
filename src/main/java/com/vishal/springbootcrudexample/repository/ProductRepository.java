package com.vishal.springbootcrudexample.repository;

import com.vishal.springbootcrudexample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Integer> {

    Product findByName(String name);
}
