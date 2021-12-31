package com.vishal.springbootcrudexample.controller;


import com.vishal.springbootcrudexample.entity.Product;
import com.vishal.springbootcrudexample.model.ResponseBody;
import com.vishal.springbootcrudexample.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import java.util.logging.Logger;



//used to write business logic in a different layer
// This controller will talk to the service class
//for that we need to inject service in controller class using Autowired
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

//    private final static Logger LOGGER = Logger.getLogger(ProductController.class.getName());
    Logger logger = LoggerFactory.getLogger(ProductController.class);

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
    public ResponseEntity<ResponseBody> addProducts(@RequestBody List<Product> products) {
        try {
            List<Product> prodObj = service.saveProducts(products);
            logger.info("Product added successfully"+prodObj);
            return new ResponseEntity<>(new ResponseBody("Product Details fetched successfully",true, prodObj),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody("Product Details not found",false, null),HttpStatus.UNPROCESSABLE_ENTITY);
        }finally {
            logger.info("Executed the post add Products method");
        }
    }



    /**
     * @return
     */

//    @GetMapping(path = "/products")
//    public ResponseEntity<ResponseBody> findAllProducts() {
//        logger.debug("A DEBUG Message");
//        logger.info("An INFO Message");
//        logger.error("An ERROR Message");
//        return service.getProducts();
//    }

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

//    @RequestMapping(value = "/products",method = RequestMethod.GET)
    @GetMapping(value ="/products/{id}")
    public ResponseEntity<ResponseBody> getProduct(@PathVariable int id){
//    public Product getProduct(@RequestParam("id") int id){
        logger.info("An INFO Message" + id);
        if(id == 0 ){
            return new ResponseEntity<>(new ResponseBody("All Products are being displayed",true,service.getProducts()), HttpStatus.OK);
        }
        try{
            Product projObj = service.getProductById(id);
            logger.info("An INFO Message" + projObj);
        return new ResponseEntity<>(new ResponseBody("Product Details fetched successfully",true, projObj),HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ResponseBody("Product Details not found",false, null),HttpStatus.BAD_REQUEST);
        }
        finally {
            logger.info("API executed successfully");
        }
    }

//    @GetMapping({"/product/{name}","/products/{id}"})
//    public ResponseEntity<ResponseBody> findProductByName(@PathVariable(value = "name",required = false) String name,
//                                                          @PathVariable(value = "id",required = false) int id){
//        if (null != name && !name.isBlank()) {
//            Product prod = service.getProductByName(name);
//            if (null == prod) {
//                return new ResponseEntity<>(new ResponseBody("Not a valid name", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
//            }else{
//                return new ResponseEntity<>(new ResponseBody("Product Found",true,prod),HttpStatus.OK);
//            }
//        }else if (id != 0){
//            Product prod = service.getProductById(id);
//            if (null == prod) {
//                return new ResponseEntity<>(new ResponseBody("Not a valid input", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
//            }else{
//                return new ResponseEntity<>(new ResponseBody("Product Found",true,prod),HttpStatus.OK);
//            }
//        }
//        else {
//            return new ResponseEntity<>(new ResponseBody("Product not Found", false, Collections.emptyList()), HttpStatus.NOT_FOUND);
//        }
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
