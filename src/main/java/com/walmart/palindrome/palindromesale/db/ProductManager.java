package com.walmart.palindrome.palindromesale.db;

import com.walmart.palindrome.palindromesale.db.entity.Product;

import java.util.List;

public interface ProductManager {
    List<Product> getProducts(String key);
}
