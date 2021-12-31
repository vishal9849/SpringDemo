package com.vishal.springbootcrudexample.service;

import com.vishal.springbootcrudexample.entity.Product;
import com.vishal.springbootcrudexample.model.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> saveProducts(List<Product> products) throws Exception;

    ResponseEntity<ResponseBody> getProducts();

    Product getProductById(int id) throws Exception;

    /**
     * @param name : name of the product
     * @return Product by name
     */
    Product getProductByName(String name);

    String deleteProduct(int id);

    Product updateProduct(Product product);
}
