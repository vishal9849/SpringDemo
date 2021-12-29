package com.vishal.springbootcrudexample.controller;


import com.vishal.springbootcrudexample.entity.Product;
import com.vishal.springbootcrudexample.model.ResponseBody;
import com.vishal.springbootcrudexample.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//used to write business logic in a different layer
// This controller will talk to the service class
//for that we need to inject service in controller class using Autowired
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService service;

    //    Now we need to write rest endpoint to all the method that is present in the service
//    @RequestBody : input json can be parsed to this product object
   /* @PostMapping("/addProduct")
    public Product addProduct(@RequestBody  product) {
        return service.saveProduct(product);
    }*/



    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }


    /**
     * @return
     */

    @GetMapping("/products")
    public ResponseEntity<ResponseBody> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/customHeader")
    public ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        return new ResponseEntity<>(
                "Custom header set", headers, HttpStatus.OK);
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    //    We can also use @Request to provide id as a part of request url
    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseBody> findProductById(@PathVariable int id) {
        if( id == 0) {
            return new ResponseEntity<>(new ResponseBody("Not a valid id",false, Collections.emptyList()),HttpStatus.OK);
        }
        List<Product> product = new ArrayList<>();
        product.add(service.getProductById(id));
        return new ResponseEntity<>(new ResponseBody("Product Details fetched successfully",true, product),HttpStatus.OK);

    }

//    @GetMapping("/products/{name}")
//    public Product findProductByName(@PathVariable String name) {
//        return service.getProductByName(name);
//    }

    @PutMapping("/update")
    public Product updateProduct(@PathVariable Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
