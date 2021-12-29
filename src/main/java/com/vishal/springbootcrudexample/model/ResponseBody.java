package com.vishal.springbootcrudexample.model;

import com.vishal.springbootcrudexample.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBody {
    private String message;
    private boolean status;
//    private List<Product> product;
    private Object product;

}
