package com.walmart.palindrome.palindromesale.controller;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Sale {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Product>> getProduct(String key) {
        List<Product> found = productService.getProduct(key);
        return new ResponseEntity(found, HttpStatus.OK);
    }
}
