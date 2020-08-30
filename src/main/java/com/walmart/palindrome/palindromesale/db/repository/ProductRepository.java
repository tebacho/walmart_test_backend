package com.walmart.palindrome.palindromesale.db.repository;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {

}
