package com.vishal.springbootcrudexample.serviceImpl;

import com.vishal.springbootcrudexample.entity.Product;
import com.vishal.springbootcrudexample.model.ResponseBody;
import com.vishal.springbootcrudexample.repository.ProductRepository;
import com.vishal.springbootcrudexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) throws Exception{
        if(products.isEmpty() || products.size() == 0) {
            throw new Exception("Please enter a list of products");
        }
            return repository.saveAll(products);
    }

    public ResponseEntity<ResponseBody> getProducts() {
        return new ResponseEntity<>(new ResponseBody("Product Details fetched successfully",true, repository.findAll()),HttpStatus.OK);
    }

    public Product getProductById(int id) throws Exception {
        Optional<Product> prod = repository.findById(id);
        if(prod.isPresent()){
            return prod.get();
        }
        throw new Exception("Details not found");

    }

    /**
     * How we can customize our query by method here the findBy is the prefix
     * and the field we are querying 'name', we append it, hence - findByName
     */
    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Product with id" + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

}
