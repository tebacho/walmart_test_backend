package com.walmart.palindrome.palindromesale.service;

import com.walmart.palindrome.palindromesale.db.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProduct(String key);
}
