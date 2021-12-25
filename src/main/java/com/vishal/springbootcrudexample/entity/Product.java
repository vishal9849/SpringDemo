package com.vishal.springbootcrudexample.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
//As the product is model or entity
@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "PRODUCT_TBL")
public class Product {
//    to implement the below on wednesday
//    private Product re;
//    private List<Product> lits;

    @Id
    @GeneratedValue
//    primary key is id
    private int id;
    @NotNull
    @NotEmpty
    private String name;
    private int quantity;
    private double price;

// If we are using spring boot app we dont need to write additional logic
//    to database connection all the db related properties we can add in
//    application.properties file


}
