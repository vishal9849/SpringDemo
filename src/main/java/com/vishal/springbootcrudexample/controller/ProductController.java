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
public class ProductController {

    @Autowired
    private ProductService service;

    //    Now we need to write rest endpoint to all the method that is present in the service
//    @RequestBody : input json can be parsed to this product object
   /* @PostMapping("/addProduct")
    public Product addProduct(@RequestBody  product) {
        return service.saveProduct(product);
    }*/



//    @PostMapping("/addProducts")
//    public List<Product> addProducts(@RequestBody List<Product> products) {
//        return service.saveProducts(products);
//    }

    @PostMapping(value = {"/add","/addProducts"})
//    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }



    /**
     * @return
     */

    @GetMapping(path = "/products")
    public ResponseEntity<ResponseBody> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping(name = "Customer header", path = "/customHeader")
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
//    @GetMapping("/product/{id}")
//    public ResponseEntity<ResponseBody> findProductById(@PathVariable int id) {
//        Product product = service.getProductById(id);
//
//        if( null == product) {
//            return new ResponseEntity<>(new ResponseBody("Not a valid id", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(new ResponseBody("Product Details fetched successfully",true, product),HttpStatus.OK);
//
//    }

    @GetMapping({"/products/{inp}"})
    public ResponseEntity<ResponseBody> findProductByName(@PathVariable Object inp){
        if (inp instanceof String) {
            String o = (String) inp;
            Product prod = service.getProductByName(o);
            if (null == prod) {
                return new ResponseEntity<>(new ResponseBody("Not a valid name", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(new ResponseBody("Product Found",true,prod),HttpStatus.OK);
            }
        }else if (inp.getClass().getSimpleName().equals("Integer")){
            int id = (int) inp;
            Product prod = service.getProductById(id);
            if (null == prod) {
                return new ResponseEntity<>(new ResponseBody("Not a valid input", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(new ResponseBody("Product Found",true,prod),HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(new ResponseBody("Product Found", true, Collections.emptyList()), HttpStatus.OK);
        }
    }


    @PutMapping("/update")
    public Product updateProduct(@PathVariable Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
